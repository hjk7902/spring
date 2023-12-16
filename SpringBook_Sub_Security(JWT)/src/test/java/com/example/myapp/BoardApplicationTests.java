package com.example.myapp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.myapp.board.dao.IBoardRepository;
import com.example.myapp.board.model.Board;
import com.example.myapp.board.service.BoardService;

@SpringBootTest
class BoardApplicationTests {

	@Mock
//	@Autowired
	IBoardRepository boardRepository;
	
	@InjectMocks
	BoardService boardService;
	
	@Test
	@Disabled
	void contextLoads() {
	}
	
	@Test
//	@Transactional
	@DisplayName("Insert Article Test")
	void testInsertArticle() {
		Board board = new Board();
		board.setCategoryId(1);		board.setEmail("test@test.com");
		board.setWriter("Kildong");	board.setPassword("1234");
		board.setTitle("Test");		board.setContent("Test Content");
		boardService.insertArticle(board);
//		boardRepository.insertArticle(board);
	}
}