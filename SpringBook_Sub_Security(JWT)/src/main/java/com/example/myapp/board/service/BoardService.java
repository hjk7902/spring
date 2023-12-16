package com.example.myapp.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myapp.board.dao.IBoardRepository;
import com.example.myapp.board.model.Board;
import com.example.myapp.board.model.BoardUploadFile;

@Service
public class BoardService implements IBoardService {

	@Autowired
	IBoardRepository boardRepository;

	@Transactional
	public void insertArticle(Board board) {
		board.setBoardId(boardRepository.selectMaxArticleNo()+1);
		boardRepository.insertArticle(board);
	}
	
	@Transactional
	public void insertArticle(Board board, BoardUploadFile file) {
		board.setBoardId(boardRepository.selectMaxArticleNo()+1);
		boardRepository.insertArticle(board);
        if(file != null && file.getFileName() != null && !file.getFileName().equals("")) {
        	file.setBoardId(board.getBoardId());
        	file.setFileId(boardRepository.selectMaxFileId()+1);
        	boardRepository.insertFileData(file);
        }
	}

	@Override
	public List<Board> selectArticleListByCategory(int categoryId, int page) {
		int start = (page-1)*10 + 1;
		return boardRepository.selectArticleListByCategory(categoryId, start, start+9); // 오라클은 BETWEEN a AND b에서 a와 b모두 포함하므로 9를 더함
	}

	@Transactional
	public Board selectArticle(int boardId) {
		boardRepository.updateReadCount(boardId);
		return boardRepository.selectArticle(boardId);
	}

	@Override
	public BoardUploadFile getFile(int fileId) {
		return boardRepository.getFile(fileId);
	}
	
	@Transactional
	public void replyArticle(Board board) {
		boardRepository.updateReplyNumber(board.getMasterId(), board.getReplyNumber());
		board.setBoardId(boardRepository.selectMaxArticleNo()+1);
		board.setReplyNumber(board.getReplyNumber()+1);
		board.setReplyStep(board.getReplyStep()+1);
		boardRepository.replyArticle(board);
	}

	@Transactional
	public void replyArticle(Board board, BoardUploadFile file) {
		boardRepository.updateReplyNumber(board.getMasterId(), board.getReplyNumber());
		board.setBoardId(boardRepository.selectMaxArticleNo()+1);
		board.setReplyNumber(board.getReplyNumber()+1);
		board.setReplyStep(board.getReplyStep()+1);
		boardRepository.replyArticle(board);
        if(file != null && file.getFileName() != null && !file.getFileName().equals("")) {
        	file.setBoardId(board.getBoardId());
        	file.setFileId(boardRepository.selectMaxFileId()+1);
        	boardRepository.insertFileData(file);
        }
	}
	
	@Override
	public String getPassword(int boardId) {
		return boardRepository.getPassword(boardId);
	}

	@Override
	public void updateArticle(Board board) {
		boardRepository.updateArticle(board);
	}

	@Transactional
	public void updateArticle(Board board, BoardUploadFile file) {
		boardRepository.updateArticle(board);
        if(file != null && file.getFileName() != null && !file.getFileName().equals("")) {
        	file.setBoardId(board.getBoardId());

        	if(file.getFileId()>0) {
        		boardRepository.updateFileData(file);
        	}else {
        		file.setFileId(boardRepository.selectMaxFileId()+1);
        		boardRepository.insertFileData(file);
        	}
        }
	}
	
	@Override
	public Board selectDeleteArticle(int boardId) {
		return boardRepository.selectDeleteArticle(boardId);
	}
	
	@Transactional
	public void deleteArticle(int boardId, int replyNumber) {
		if(replyNumber>0) {
			boardRepository.deleteReplyFileData(boardId);
			boardRepository.deleteArticleByBoardId(boardId);
		}else if(replyNumber==0){
			boardRepository.deleteFileData(boardId);
			boardRepository.deleteArticleByMasterId(boardId);
		}else {
			throw new RuntimeException("WRONG_REPLYNUMBER");
		}
	}

	@Override
	public int selectTotalArticleCount() {
		return boardRepository.selectTotalArticleCount();
	}

	@Override
	public int selectTotalArticleCountByCategoryId(int categoryId) {
		return boardRepository.selectTotalArticleCountByCategoryId(categoryId);
	}

	@Override
	public List<Board> searchListByContentKeyword(String keyword, int page) {
		int start = (page-1)*10 + 1;
		return boardRepository.searchListByContentKeyword("%"+keyword+"%", start, start+9); // 오라클은 BETWEEN a AND b에서 a와 b모두 포함하므로 9를 더함
	}

	@Override
	public int selectTotalArticleCountByKeyword(String keyword) {
		return boardRepository.selectTotalArticleCountByKeyword("%"+keyword+"%");
	}

}