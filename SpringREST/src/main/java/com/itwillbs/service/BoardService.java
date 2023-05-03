package com.itwillbs.service;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardService {
	
	// 디비 시간정보 조회
	public String itwillbs_getTime();
	
	// 게시판 글쓰기 
	public void writeBoard(BoardVO vo) throws Exception;
	
	// 모든 게시판 글 목록 가져오기
	public List<BoardVO> getBoardListAll() throws Exception;
	
	// 게시판 글 조회수 1증가
	public void incrementViewCnt(Integer bno) throws Exception;
	
	// 게시판 특정 글 가져오기
	public BoardVO getBoard(Integer bno) throws Exception;
	
	// 게시판 글 수정하기
	public Integer modifyBoard(BoardVO uvo) throws Exception;
	
	// 게시판 글 삭제하기
	public Integer removeBoard(Integer bno) throws Exception;
	
	// 게시판 글 전체 갯수 
	public int countPage() throws Exception;
	
	// 글 목록 조회
	public List<BoardVO> getBoardListPage(Integer page) throws Exception;
}
