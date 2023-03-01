package com.example.myapp.test.dao;

import java.util.List;

import com.example.myapp.test.model.SampleVO;

public interface ISampleRepository {

	List<SampleVO> selectSampleData();
}
