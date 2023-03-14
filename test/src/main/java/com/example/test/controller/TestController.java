package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.test.service.ITestService;

@Controller
public class TestController {

	// TODO 5. 서비스 빈을 의존성 주입받도록 아노테이션을 추가하세요.
	
	ITestService testService;
	
	// TODO 6. URL이 /count가 요청되었을 때 아래의 메서드가 실행되도록 요청 매핑을 추가하세요.
	
	public String rowCount(Model model) {
		int rowCount = testService.getRowCount();
		System.out.println(rowCount);
		model.addAttribute("rowcount", rowCount);
		return "count";
	}
	
}
