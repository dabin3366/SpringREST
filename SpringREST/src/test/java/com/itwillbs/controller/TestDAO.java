package com.itwillbs.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.itwillbs.persistence.BoardDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"}
		)
public class TestDAO {
	private static final Logger logger = LoggerFactory.getLogger(TestDAO.class);
	
	@Autowired
	private BoardDAO bdao;
	
	@Test
	public void testDAOConnect() throws Exception{
		logger.info(bdao+"@@@@@@@@@@@@@@");
	}
	
}
