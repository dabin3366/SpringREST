package com.itwillbs.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	private static final Logger logger = LoggerFactory.getLogger(BoardDAOImpl.class);
	
	// mapper Namespace
	private static final String NAMESPACE ="com.itwillbs.mapper.BoardMapper";
	
	// 디비연결 정보 객체 <- 주입
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public String getDBTime() {
		// 디비연결
		logger.info("디비연결정보 : "+sqlSession);
		// sql 실행
		// String result = sqlSession.selectOne(NAMESPACE+".getTime");
		// 결과 리턴
		// return result;
		return sqlSession.selectOne(NAMESPACE+".getTime");
	}
	
	@Override
	public void createBoard(BoardVO vo) throws Exception {
		logger.info(" S -> DAO : 글쓰기 정보 전달");
		
		logger.info(" DAO -> mapper -> DB : SQL 실행 ");
		sqlSession.insert(NAMESPACE+".create",vo);
		
		logger.info(" DAO -> S : 결과 전달 ");
	}
	
	@Override
	public List<BoardVO> getBoardListAll() throws Exception {
		// 서비스 호출로 DAO는 mapper의 쿼리 호출 정보 저장
		
		return sqlSession.selectList(NAMESPACE+".listAll");
	}
	
	@Override
	public void incrementViewCnt(Integer bno) throws Exception {
		sqlSession.update(NAMESPACE+".addViewCnt", bno);
	}
	
	@Override
	public BoardVO getBoard(Integer bno) throws Exception {
		
		return sqlSession.selectOne(NAMESPACE+".getBoard", bno);
	}
	
	@Override
	public Integer modifyBoard(BoardVO uvo) throws Exception {
		return sqlSession.update(NAMESPACE+".modifyBoard",uvo);
	}
	
	@Override
	public Integer deleteBoard(Integer bno) throws Exception {
		return sqlSession.delete(NAMESPACE+".removeBoard", bno);
	}
	
	@Override
	public List<BoardVO> getBoardListPage(Integer page) throws Exception {
		logger.info("page : "+page);
		
		if(page <= 0) {
			page = 1;
		}
		// 페이징 처리 정보 계산(글번호 인덱스 계산)
		// 0   10   20   30.....
		page = (page-1) * 10;
		
		return sqlSession.selectList(NAMESPACE+".listPage",page);
	}
	
	@Override
	public int countPage() throws Exception {
		return sqlSession.selectOne(NAMESPACE+".countPage");
	}
	
}
