package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itwillbs.domain.TestVO;

@RestController
public class SampleRESTController {
	private static final Logger logger = LoggerFactory.getLogger(SampleRESTController.class);
	
	// http://localhost:8080/test1
	@GetMapping("/test1")
	public void method() throws Exception{
		logger.info("method() 호출 1");
	}
	
	// http://localhost:8080/test2
	// 리턴타입 앞에는 @ResponseBody 생략되어있다.(RestController)
	// String 리턴하면 => 문자열 리턴
	@GetMapping("/test2")
	public int method2() throws Exception{
		logger.info("method2() 호출 1");
		return 100;
	}
	
	// http://localhost:8080/test3
	@GetMapping("/test3")
	public TestVO method3() throws Exception{
		logger.info("method3() 호출 1");
		// 일반객체 => JSON 객체 형태로 변환
		
		TestVO testVO = new TestVO();
		testVO.setNo(1);
		testVO.setName("ITWLIASDLA");
		
		return testVO;
	}
	
	// http://localhost:8080/test4
	@GetMapping("/test4")
	public List<TestVO> method4() throws Exception{
		logger.info("method4() 호출 1");
		
		List<TestVO> list = new ArrayList<TestVO>();
		
		for(int i=0;i<10;i++) {
			TestVO testVO = new TestVO();
			testVO.setNo(i);
			testVO.setName("ITWILL"+i);
			
			list.add(testVO);
		}
		
		return list;
	}
	
	// http://localhost:8080/test5
		@GetMapping("/test5")
		public Map<Integer, TestVO> method5() throws Exception{
			logger.info("method5() 호출 1");
			
			Map<Integer, TestVO> map = new HashMap<Integer, TestVO>();
			
			for(int i=0;i<10;i++) {
				TestVO testVO = new TestVO();
				testVO.setNo(i);
				testVO.setName("ITWILL"+i);
				
				map.put(i, testVO);
			}
			
			return map;
		}
		// http://localhost:8080/orders/itwill (문자데이터 처리가능)
		@GetMapping("/orders/{num}")
		public String method6(@PathVariable("num") String tmp) throws Exception{
			logger.info("method6() 호출");
			
			return tmp;
		}
		
		@PostMapping("/restInput")
		public String restInputPOST(@RequestBody TestVO vo) throws Exception{
			logger.info("restInputPOST() 호출 ");
			// @RequestBody : JSON형태로 전달된 데이터를 매핑되는 객체가 있다면
			//                해당 객체로 자동 변환
			logger.info(vo+"");
			
			return "";
		}
	
	
	
	
}
