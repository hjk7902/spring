package com.example.demo.hr.dao;

import java.util.List;
import java.util.Map;

import com.example.demo.hr.model.Emp;

public interface IEmpRepository {
	int getEmpCount();		//모든 사원의 수 조회
	int getEmpCount(int deptid);	//지정한 부서의 사원 수 조회
	List<Emp> getEmpList();		//모든 사원의 정보 조회
	Emp getEmpInfo(int empid);	//지정한 사원의 모든 정보 조회
	void updateEmp(Emp emp);		//지정한 사원의 정보 수정
	void insertEmp(Emp emp);		//새로운 사원의 정보 입력
	void deleteJobHistory(int empid);//사원의 직무이력 변경내용 삭제
	int deleteEmp(int empid, String email);//사원의 정보 삭제
	List<Map<String, Object>> getAllDeptId();	//모든 부서아이디와 이름 조회
	List<Map<String, Object>> getAllJobId();	//모든 직무아이디와 타이틀 조회
	List<Map<String, Object>> getAllManagerId();//모든 매니저아이디와 이름 조회
}
