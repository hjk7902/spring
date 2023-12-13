package com.example.demo.hr.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.hr.dao.IEmpRepository;
import com.example.demo.hr.model.Emp;

@Service
public class EmpService implements IEmpService {

	@Autowired
	IEmpRepository empRepository;
	
	@Override
	public int getEmpCount() {
		return empRepository.getEmpCount();
	}

	@Override
	public int getEmpCount(int deptid) {
		return empRepository.getEmpCount(deptid);
	}

	@Override
	public List<Emp> getEmpList() {
		return empRepository.getEmpList();
	}

	@Override
	public Emp getEmpInfo(int empid) {
		return empRepository.getEmpInfo(empid);
	}

	@Override
	public void updateEmp(Emp emp) {
		empRepository.updateEmp(emp);
	}

	@Override
	public void insertEmp(Emp emp) {
		empRepository.insertEmp(emp);
	}

	@Override
	@Transactional
	public int deleteEmp(int empid, String email) {
		empRepository.deleteJobHistory(empid);
		return empRepository.deleteEmp(empid, email);
	}

	@Override
	public List<Map<String, Object>> getAllDeptId() {
		return empRepository.getAllDeptId();
	}

	@Override
	public List<Map<String, Object>> getAllJobId() {
		return empRepository.getAllJobId();
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {
		return empRepository.getAllManagerId();
	}

}
