package com.example.myapp.board.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.myapp.board.model.Board;
import com.example.myapp.board.model.BoardCategory;
import com.example.myapp.board.model.BoardUploadFile;
import com.example.myapp.board.service.IBoardCategoryService;
import com.example.myapp.board.service.IBoardService;

import jakarta.servlet.http.HttpServletRequest; // tomcat 9이하면 javax.servlet
import jakarta.servlet.http.HttpSession;

@Controller
public class BoardController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	IBoardService boardService;
	
	@Autowired
	IBoardCategoryService categoryService;
		
	@GetMapping("/board/cat/{categoryId}/{page}")
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
		int totalPageBlock = (int)(Math.ceil(totalPage/10.0));
		int nowPageBlock = (int) Math.ceil(page/10.0);
		int startPage = (nowPageBlock-1)*10 + 1;
		int endPage = 0;
		if(totalPage > nowPageBlock*10) {
			endPage = nowPageBlock*10;
		}else {
			endPage = totalPage;
		}
		model.addAttribute("totalPageCount", totalPage);
		model.addAttribute("nowPage", page);
		model.addAttribute("totalPageBlock", totalPageBlock);
		model.addAttribute("nowPageBlock", nowPageBlock);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		return "board/list";
	}

	@GetMapping("/board/cat/{categoryId}")
	public String getListByCategory(@PathVariable int categoryId, HttpSession session, Model model) {
		return getListByCategory(categoryId, 1, session, model);
	}
	
	@GetMapping("/board/{boardId}/{page}")
	public String getBoardDetails(@PathVariable int boardId, @PathVariable int page, Model model) {
		Board board = boardService.selectArticle(boardId);
		String fileName = board.getFileName();
		if(fileName!=null) {
			int fileLength = fileName.length();
			String fileType = fileName.substring(fileLength-4, fileLength).toUpperCase();
			model.addAttribute("fileType", fileType);
		}
		model.addAttribute("board", board);
		model.addAttribute("page", page);
		model.addAttribute("categoryId", board.getCategoryId());
		logger.info("getBoardDetails " + board.toString());
		return "board/view";
	}

	@GetMapping("/board/{boardId}")
	public String getBoardDetails(@PathVariable int boardId, Model model) {
		return getBoardDetails(boardId, 1, model);
	}
	
	@GetMapping(value="/board/write/{categoryId}")
	public String writeArticle(@PathVariable int categoryId, HttpSession session, Model model) {
		// CSRF 토큰을 생성하여 세션에 저장
		String csrfToken = UUID.randomUUID().toString();
        session.setAttribute("csrfToken", csrfToken);
		List<BoardCategory> categoryList = categoryService.selectAllCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", categoryId);
		return "board/write";
	}
	
	@PostMapping(value="/board/write")
	public String writeArticle(Board board, BindingResult results, String csrfToken, HttpSession session, RedirectAttributes redirectAttrs) {
		logger.info("/board/write : " + board.toString() + csrfToken);
		String sessionToken = (String) session.getAttribute("csrfToken");
		if(csrfToken==null || !csrfToken.equals(sessionToken)) {
			throw new RuntimeException("CSRF Token Error.");
		}
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

	@GetMapping("/file/{fileId}")
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
	
	@GetMapping(value="/board/reply/{boardId}")
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
	
	@PostMapping(value="/board/reply")
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

	@GetMapping(value="/board/update/{boardId}")
	public String updateArticle(@PathVariable int boardId, Model model) {
		List<BoardCategory> categoryList = categoryService.selectAllCategory();
		Board board = boardService.selectArticle(boardId);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryId", board.getCategoryId());
		board.setContent(board.getContent().replaceAll("<br>", "\r\n"));
		model.addAttribute("board", board);
		return "board/update";
	}

	@PostMapping(value="/board/update")
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

	@GetMapping(value="/board/delete/{boardId}")
	public String deleteArticle(@PathVariable int boardId, Model model) {
		Board board = boardService.selectDeleteArticle(boardId);
		model.addAttribute("categoryId", board.getCategoryId());
		model.addAttribute("boardId", boardId);
		model.addAttribute("replyNumber", board.getReplyNumber());
		return "board/delete";
	}
	
	@PostMapping(value="/board/delete")
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

	@GetMapping("/board/search/{page}")
	public String search(@RequestParam(required=false, defaultValue="") String keyword, @PathVariable int page, HttpSession session, Model model) {
		try {
			List<Board> boardList = boardService.searchListByContentKeyword(keyword, page);
			model.addAttribute("boardList", boardList);
			int bbsCount = boardService.selectTotalArticleCountByKeyword(keyword);
			int totalPage = 0;
			if(bbsCount > 0) {
				totalPage= (int)Math.ceil(bbsCount/10.0);
			}
			int totalPageBlock = (int)(Math.ceil(totalPage/10.0));
			int nowPageBlock = (int) Math.ceil(page/10.0);
			int startPage = (nowPageBlock-1)*10 + 1;
			int endPage = 0;
			if(totalPage > nowPageBlock*10) {
				endPage = nowPageBlock*10;
			}else {
				endPage = totalPage;
			}
			model.addAttribute("keyword", keyword);
			model.addAttribute("totalPageCount", totalPage);
			model.addAttribute("nowPage", page);
			model.addAttribute("totalPageBlock", totalPageBlock);
			model.addAttribute("nowPageBlock", nowPageBlock);
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "board/search";
	}

	@ExceptionHandler({RuntimeException.class})
	public String error(HttpServletRequest request, Exception ex, Model model) {
		model.addAttribute("exception", ex);
		model.addAttribute("stackTrace", ex.getStackTrace());
		model.addAttribute("url", request.getRequestURI());
		return "error/runtime";
	}
	
	@GetMapping("/json/{boardId}")
	@ResponseBody
	public Board getBoardDetailsJSON(@PathVariable int boardId) {
		Board board = boardService.selectArticle(boardId);
		return board;
	}
	
	@GetMapping("/json2/{boardId}")
	@ResponseBody
	public Map<String, Object> getBoardDetailsJSON2(@PathVariable int boardId) {
		Map<String, Object> myMap = new HashMap<String, Object>();
		Board board = boardService.selectArticle(boardId);
		myMap.put("board", board);
		myMap.put("obj", "other object");
		return myMap;
	}
}