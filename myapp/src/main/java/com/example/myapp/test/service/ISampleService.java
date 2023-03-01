package com.example.myapp.test.service;

import java.util.List;

import com.example.myapp.test.model.SampleVO;

public interface ISampleService {
	List<SampleVO> selectSampleData();
}