package com.itwillbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
		
		// Rest방식은 별도의 View 페이지가 없음
		// => 예외상황 발생(알기어려움) => HttpStatus코드
		// ResponseEntity 객체사용 (데이터(리소스)+Http 상태)
		
		// http://localhost:8080/test6
		@GetMapping("/test6")
		public ResponseEntity<Void> method6() throws Exception{
			logger.info("method6() 호출 ");
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		// http://localhost:8080/test7
		@GetMapping("/test7")
		public ResponseEntity<TestVO> method7() throws Exception{
			logger.info("method7() 호출 ");
			
			TestVO vo = new TestVO();
			vo.setName("아이티윌");
			vo.setNo(1000);
			
			if(vo == null) {
				return new ResponseEntity<TestVO>(vo,HttpStatus.NOT_FOUND);
			}else {
				return new ResponseEntity<TestVO>(vo,HttpStatus.OK);
			}
		}
		
		// http://localhost:8080/test8
		@GetMapping("/test8")
		public ResponseEntity<List<TestVO>> method8() throws Exception{
			logger.info("method8() 호출 ");
			
			List<TestVO> list = new ArrayList<TestVO>();
			for(int i=0;i<5;i++) {
				TestVO vo = new TestVO();
				vo.setName("아이티윌");
				vo.setNo(1000);
				list.add(vo);
			}
			return new ResponseEntity<List<TestVO>>(list,HttpStatus.OK);
		}
		// http://localhost:8080/test9
		@GetMapping("/test9")
		public ResponseEntity method9() throws Exception{
			HttpHeaders respHeader = new HttpHeaders();
			respHeader.add("Content-Type", "text/html; charset=utf-8");
			
			// JS코드
			String msg = "";
			msg += "<script>";
			msg += "alert();";
			msg += "location.href='./test2';";
			msg += "</script>";
			
			return new ResponseEntity(msg,respHeader,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	
	
	
}
