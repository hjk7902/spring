package com.example.myapp.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.board.dao.IBoardCategoryRepository;
import com.example.myapp.board.model.BoardCategory;

@Service
public class BoardCategoryService implements IBoardCategoryService{

	@Autowired
	IBoardCategoryRepository boardCategoryRepository;
	
	@Override
	public List<BoardCategory> selectAllCategory() {
		return boardCategoryRepository.selectAllCategory();
	}

	@Override
	public void insertNewCategory(BoardCategory boardCategory) {
		int newCategoryId = boardCategoryRepository.selectMaxCategoryId() + 1;
		boardCategory.setCategoryId(newCategoryId);
		boardCategoryRepository.insertNewCategory(boardCategory);
	}

	@Override
	public void updateCategory(BoardCategory boardCategory) {
		boardCategoryRepository.updateCategory(boardCategory);
	}

	@Override
	public void deleteCategory(int categoryId) {
		boardCategoryRepository.deleteCategory(categoryId);
	}
}