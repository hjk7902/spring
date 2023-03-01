package com.example.myapp.test.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myapp.test.model.SampleVO;
import com.example.myapp.test.service.ISampleService;

@Controller
public class SampleController {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ISampleService sampleService;
	
	@RequestMapping("/sample/list")
	public String getListData(Model model) {
		List<SampleVO> sampleList = sampleService.selectSampleData();
		model.addAttribute("sampleList", sampleList);
		logger.info("get sample list");
		return "list";
	}

}