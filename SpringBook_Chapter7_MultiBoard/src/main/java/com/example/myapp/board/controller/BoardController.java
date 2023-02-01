package com.example.myapp.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myapp.board.model.Board;
import com.example.myapp.board.model.BoardCategory;
import com.example.myapp.board.model.BoardUploadFile;
import com.example.myapp.board.service.IBoardCategoryService;
import com.example.myapp.board.service.IBoardService;

@Controller
public class BoardController {
	static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	IBoardService boardService;
	
	@Autowired
	IBoardCategoryService categoryService;
		
	@RequestMapping("/board/cat/{categoryId}/{page}")
	public String getListByCategory(@PathVariable int categoryId, @PathVariable int page, HttpSession session, Model model) {
		session.setAttribute("page", page);
		model.addAttribute("categoryId", categoryId);
		List<Board> boardList = boardService.selectArticleListByCategory(categoryId, page);
		model.addAttribute("boardList", boardList);
		int bbsCount = boardService.selectTotalArticleCountByCategoryId(categoryId);
		int totalPage = 0;
		if(bbsCount > 0) {
			totalPage= (int)Math.ceil(bbsCount/10.0);
		}
		model.addAttribute("totalPageCount", totalPage);
		model.addAttribute("page", page);
		return "board/list";
	}

	@RequestMapping("/board/cat/{categoryId}")
	public String getListByCategory(@PathVariable int categoryId, HttpSession session, Model model) {
		return getListByCategory(categoryId, 1, session, model);
	}
	
	@RequestMapping("/board/{boardId}/{page}")
	public String getBoardDetails(@PathVariable int boardId, @PathVariable int page, Model model) {
		Board board = boardService.selectArticle(boardId);
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		model.addAttribute("categoryId", board.getCategoryId());
		logger.info("getBoardDetails " + board.toString());
		return "board/view";
	}

	@RequestMapping("/board/{boardId}")
	public String getBoardDetails(@PathVariable int boardId, Model model) {
		return getBoardDetails(boardId, 1, model);
	}
	
	@RequestMapping(value="/board/write/{categoryId}", method=RequestMethod.GET)
	public String writeArticle(@PathVariable int categoryId, Model model) {
		List<BoardCategory> categoryList = categoryService.selectAllCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", categoryId);
		return "board/write";
	}
	
	@RequestMapping(value="/board/write", method=RequestMethod.POST)
	public String writeArticle(Board board, BindingResult results, RedirectAttributes redirectAttrs) {
		logger.info("/board/write : " + board.toString());
		try{
			board.setContent(board.getContent().replace("\r\n", "<br>"));
			board.setTitle(Jsoup.clean(board.getTitle(), Safelist.basic()));
			board.setContent(Jsoup.clean(board.getContent(), Safelist.basic()));
			MultipartFile mfile = board.getFile();
			if(mfile!=null && !mfile.isEmpty()) {
				BoardUploadFile file = new BoardUploadFile();
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				boardService.insertArticle(board, file);
			}else {
				boardService.insertArticle(board);
			}
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/board/cat/"+board.getCategoryId();
	}

	@RequestMapping("/file/{fileId}")
	public ResponseEntity<byte[]> getFile(@PathVariable int fileId) {
		BoardUploadFile file = boardService.getFile(fileId);
		logger.info("getFile " + file.toString());
		final HttpHeaders headers = new HttpHeaders();
		String[] mtypes = file.getFileContentType().split("/");
		headers.setContentType(new MediaType(mtypes[0], mtypes[1]));
		headers.setContentLength(file.getFileSize());
		try {
			String encodedFileName = URLEncoder.encode(file.getFileName(), "UTF-8");
			headers.setContentDispositionFormData("attachment", encodedFileName);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return new ResponseEntity<byte[]>(file.getFileData(), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/board/reply/{boardId}", method=RequestMethod.GET)
	public String replyArticle(@PathVariable int boardId, Model model) {
		Board board = boardService.selectArticle(boardId);
		board.setWriter("");
		board.setEmail("");
		board.setTitle("[Re]"+board.getTitle());
		board.setContent("\n\n\n----------\n" + board.getContent().replaceAll("<br>", "\n"));
		model.addAttribute("board", board);
		model.addAttribute("next", "reply");
		return "board/reply";
	}
	
	@RequestMapping(value="/board/reply", method=RequestMethod.POST)
	public String replyArticle(Board board, RedirectAttributes redirectAttrs, HttpSession session) {
		logger.info("/board/reply : " + board.toString());
		try{
			board.setContent(board.getContent().replace("\r\n", "<br>"));
			board.setTitle(Jsoup.clean(board.getTitle(), Safelist.basic()));
			board.setContent(Jsoup.clean(board.getContent(), Safelist.basic()));
			MultipartFile mfile = board.getFile();
			if(mfile!=null && !mfile.isEmpty()) {
				BoardUploadFile file = new BoardUploadFile();
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				boardService.replyArticle(board, file);
			}else {
				boardService.replyArticle(board);
			}
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		if(session.getAttribute("page") != null) {
			return "redirect:/board/cat/"+board.getCategoryId() + "/" + (Integer)session.getAttribute("page");
		}else {
			return "redirect:/board/cat/"+board.getCategoryId(); 
		}
	}

	@RequestMapping(value="/board/update/{boardId}", method=RequestMethod.GET)
	public String updateArticle(@PathVariable int boardId, Model model) {
		List<BoardCategory> categoryList = categoryService.selectAllCategory();
		Board board = boardService.selectArticle(boardId);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", board.getCategoryId());
		board.setContent(board.getContent().replaceAll("<br>", "\r\n"));
		model.addAttribute("board", board);
		return "board/update";
	}

	@RequestMapping(value="/board/update", method=RequestMethod.POST)
	public String updateArticle(Board board, RedirectAttributes redirectAttrs) {
		logger.info("/board/update " + board.toString());
		String dbPassword = boardService.getPassword(board.getBoardId());
		if(!board.getPassword().equals(dbPassword)) {
			redirectAttrs.addFlashAttribute("passwordError", "게시글 비밀번호가 다릅니다");
			return "redirect:/board/update/" + board.getBoardId();
		}
		try{
			board.setContent(board.getContent().replace("\r\n", "<br>"));
			board.setTitle(Jsoup.clean(board.getTitle(), Safelist.basic()));
			board.setContent(Jsoup.clean(board.getContent(), Safelist.basic()));
			MultipartFile mfile = board.getFile();
			if(mfile!=null && !mfile.isEmpty()) {
				logger.info("/board/update : " + mfile.getOriginalFilename());
				BoardUploadFile file = new BoardUploadFile();
				file.setFileId(board.getFileId());
				file.setFileName(mfile.getOriginalFilename());
				file.setFileSize(mfile.getSize());
				file.setFileContentType(mfile.getContentType());
				file.setFileData(mfile.getBytes());
				logger.info("/board/update : " + file.toString());
				boardService.updateArticle(board, file);
			}else {
				boardService.updateArticle(board);
			}
		}catch(Exception e){
			e.printStackTrace();
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/board/"+board.getBoardId();
	}

	@RequestMapping(value="/board/delete/{boardId}", method=RequestMethod.GET)
	public String deleteArticle(@PathVariable int boardId, Model model) {
		Board board = boardService.selectDeleteArticle(boardId);
		model.addAttribute("categoryId", board.getCategoryId());
		model.addAttribute("boardId", boardId);
		model.addAttribute("replyNumber", board.getReplyNumber());
		return "board/delete";
	}
	
	@RequestMapping(value="/board/delete", method=RequestMethod.POST)
	public String deleteArticle(Board board, HttpSession session, RedirectAttributes model) {
		try {
			String dbpw = boardService.getPassword(board.getBoardId());
			if(dbpw.equals(board.getPassword())) {
				boardService.deleteArticle(board.getBoardId(), board.getReplyNumber());
				return "redirect:/board/cat/" + board.getCategoryId() + "/" + (Integer)session.getAttribute("page");
			}else {
				model.addFlashAttribute("message", "WRONG_PASSWORD_NOT_DELETED");
				return "redirect:/board/delete/" + board.getBoardId();
			}
		}catch(Exception e){
			model.addAttribute("message", e.getMessage());
			e.printStackTrace();
			return "error/runtime";
		}
	}

	@RequestMapping("/board/search/{page}")
	public String search(@RequestParam(required=false, defaultValue="") String keyword, @PathVariable int page, HttpSession session, Model model) {
		try {
			List<Board> boardList = boardService.searchListByContentKeyword(keyword, page);
			model.addAttribute("boardList", boardList);
			int bbsCount = boardService.selectTotalArticleCountByKeyword(keyword);
			int totalPage = 0;

			if(bbsCount > 0) {
				totalPage= (int)Math.ceil(bbsCount/10.0);
			}
			model.addAttribute("totalPageCount", totalPage);
			model.addAttribute("page", page);
			model.addAttribute("keyword", keyword);
			logger.info(totalPage + ":" + page + ":" + keyword);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "board/search";
	}

}