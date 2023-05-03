package com.itwillbs.persistence;

import java.util.List;

import com.itwillbs.domain.BoardVO;

public interface BoardDAO {
	// 필요한 동작을 추상메서드로 정의
	
	// 디비 시간정보 조회
	public String getDBTime();
	
	// 게시판 글쓰기
	public void createBoard(BoardVO vo) throws Exception;
	
	// 게시판 글 전체 목록
	public List<BoardVO> getBoardListAll() throws Exception;
	
	// 게시판 글 조회수 1증가
	public void incrementViewCnt(Integer bno) throws Exception;
	
	// 특정 게시글 조회
	public BoardVO getBoard(Integer bno) throws Exception;
	
	// 게시판 글 수정
	public Integer modifyBoard(BoardVO uvo) throws Exception;
	
	// 게시판 글 삭제
	public Integer deleteBoard(Integer bno) throws Exception;
	
	// 글 페이징 처리(페이지 번호)
	public List<BoardVO> getBoardListPage(Integer page) throws Exception;
	
	// 게시판 글 전체 갯수
	public int countPage() throws Exception;
	
}
