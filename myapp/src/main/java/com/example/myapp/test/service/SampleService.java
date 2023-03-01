package com.example.myapp.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.test.dao.ISampleRepository;
import com.example.myapp.test.model.SampleVO;

@Service
public class SampleService implements ISampleService {

	@Autowired
	ISampleRepository boardRepository;

	@Override
	public List<SampleVO> selectSampleData() {
		return boardRepository.selectSampleData();
	}
}