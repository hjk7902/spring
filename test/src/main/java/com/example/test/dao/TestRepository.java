package com.example.test.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestRepository implements ITestRepository{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int getRowCount() {
		// TODO 2. EMPLOYEES 테이블의 모든 행의 수를 조회하는 SQL 구문을 작성하세요.
		String sql = ""; 
		
		// TODO 3. return 구문을 완성하세요.
		return 0;
	}

}
