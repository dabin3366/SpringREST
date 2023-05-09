package com.itwillbs.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.service.BoardService;

@RestController
@RequestMapping("/boards")
public class BoardRestController {
	private static final Logger logger = LoggerFactory.getLogger(BoardRestController.class);

	@Inject
	private BoardService service;
	
	// 글쓰기 처리  /boards + JSON데이터 (POST)
	@RequestMapping(value = "",method = RequestMethod.POST)
	public ResponseEntity<String> insertBoard(@RequestBody BoardVO vo) throws Exception{
		logger.info("insertBoard() 실행");
		
		logger.info(vo.toString());
		
		service.writeBoard(vo);
		
		return new ResponseEntity<String>("addOK",HttpStatus.OK); //데이터+상태
	}
	
	// 특정 글 조회   /boards/100  (GET)
	@RequestMapping(value = "/{bno}",method = RequestMethod.GET)
	public ResponseEntity<BoardVO> readBoard(@PathVariable("bno") int bno) throws Exception{
		logger.info(" readBoard() 호출 ! ");
		logger.info(" bno : "+bno);
		
		BoardVO vo = service.getBoard(bno);
		
		return new ResponseEntity<BoardVO>(vo,HttpStatus.OK);
	}
	
	// 글목록 조회     /boards/1/list  (get)
	@RequestMapping(value = "/{page}/list",method = RequestMethod.GET)
	public ResponseEntity<List<BoardVO>> getBoardList(@PathVariable("page") Integer page) throws Exception{
		logger.info(" getBoardList() 호출 ");
		
		List<BoardVO> boardList = service.getBoardListPage(page);
		
		return new ResponseEntity<List<BoardVO>>(boardList,HttpStatus.OK);
	}
	
	// 글정보 수정		/boards/100 + 수정데이터(json)   	(PUT)
	@RequestMapping(value = "/{bno}",method = RequestMethod.PUT)
	public ResponseEntity<String> updateBoard(@PathVariable("bno") int bno,
									@RequestBody BoardVO uvo) throws Exception{
		
		int result = 0;
		ResponseEntity<String> respEntity = null;
		logger.info(" bno : "+bno);
		logger.info(""+uvo);
		try {
			logger.info( "updateBoard(bno,uvo) 호출 ");
			
		    result = service.modifyBoard(uvo);
			
			if(result == 1) { // 수정 완료
				//return new ResponseEntity<String>("modOK",HttpStatus.OK);		
				respEntity = new ResponseEntity<String>("modOK",HttpStatus.OK);		
			}
		}catch (Exception e) {
			if(result != 1) { // 수정 실패
				//return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);		
				respEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);		
			}
		}
		
		return respEntity;
	}// updateBoard
	
	
	// 글삭제     /boards/100		(DELETE)
	@RequestMapping(value = "/{bno}",method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteBoard(@PathVariable("bno") int bno) throws Exception{
		logger.info(" deleteBoard() 호출 " );
		
		service.removeBoard(bno);
		
		return new ResponseEntity<String>("delOK",HttpStatus.OK);
	}
	
	
	
	
	
	
}
