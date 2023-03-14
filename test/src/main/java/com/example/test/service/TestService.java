package com.example.test.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.dao.ITestRepository;

@Service
public class TestService implements ITestService {
    @Autowired
    ITestRepository testRepository;

    @Override
	public int getRowCount() {
    	return testRepository.getRowCount();
    }
}
