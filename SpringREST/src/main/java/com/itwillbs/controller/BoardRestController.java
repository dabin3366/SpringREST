package com.itwillbs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardRestController {
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);
	
	@Autowired
	private BoardService service;
	
	// 글쓰기 처리 /boards + JSON데이터
	@PostMapping("")
	public ResponseEntity<String> insertBoard(@RequestBody BoardVO vo) throws Exception{
		logger.info("insertBoard() 호출 ");
		
		logger.info(vo+"");
		
		service.writeBoard(vo);
		
		return new ResponseEntity<String>("add OKOKOKOk",HttpStatus.OK); // 데이터 + 상태
	}
	
	// 특정 글 조회 /boards/100
	@GetMapping("/{bno}")
	public ResponseEntity<BoardVO> readBoard(@PathVariable("bno") int bno) throws Exception{
		logger.info("readBoard() 호출");
		logger.info(bno+"");
		
		BoardVO vo = service.getBoard(bno);
		return new ResponseEntity<BoardVO>(vo,HttpStatus.OK);
	}
	
	// 글 목록 조회 /boards/1/list
	@GetMapping("/{page}/list")
	public ResponseEntity<List<BoardVO>> getBoardList(@PathVariable("page") int page) throws Exception{
		logger.info("getBoardList() 호출");
		logger.info(page+"");
		List<BoardVO> boardList = service.getBoardListPage(page);
		
		return new ResponseEntity<List<BoardVO>>(boardList,HttpStatus.OK);
	}
	
	 
}
