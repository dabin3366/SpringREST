package com.itwillbs.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itwillbs.domain.BoardVO;
import com.itwillbs.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService{
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Inject
	private BoardDAO bdao;
	
	
	@Override
	public String itwillbs_getTime() {
		return bdao.getDBTime();
	}
	
	@Override
	public void writeBoard(BoardVO vo) throws Exception {
		logger.info(" C -> S : 글정보 전달");
		
		logger.info(" S -> DAO : 글정보 전달");
		bdao.createBoard(vo);
		logger.info(" S -> C : 결과 전달");
	}
	
	@Override
	public List<BoardVO> getBoardListAll() throws Exception {
		// 컨트롤러의 호출로 DAO호출
		
		return bdao.getBoardListAll();
	}
	
	@Override
	public void incrementViewCnt(Integer bno) throws Exception {
		bdao.incrementViewCnt(bno);
	}
	
	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		
		return bdao.getBoard(bno);
	}
	
	@Override
	public Integer modifyBoard(BoardVO uvo) throws Exception {
		return bdao.modifyBoard(uvo);
	}
	
	@Override
	public Integer removeBoard(Integer bno) throws Exception {
		return bdao.deleteBoard(bno);
	}
	
	@Override
	public int countPage() throws Exception {
		return bdao.countPage();
	}
	
	@Override
	public List<BoardVO> getBoardListPage(Integer page) throws Exception {
		List<BoardVO> list = bdao.getBoardListPage(page);
		return list;
	}
}
