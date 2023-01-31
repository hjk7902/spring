package com.coderby.myapp.hr.model;

import java.sql.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

public class EmpVO {
	
	@Min(value=300, message="사원번호는 300이상여야 합니다.")
	private int employeeId;
	
	@Pattern(regexp="[a-zA-Z가-힣]{2,}", message="이름을 입력하세요.")
	private String firstName;
	
	@Pattern(regexp="[a-zA-Z가-힣]{1,}", message="성을 입력하세요.")
	private String lastName;
	
	@Pattern(regexp="[A-Z0-9]{2,}", message="이메일 주소를 입력하세요.")
	private String email;
	
	@Pattern(regexp="[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}", message="전화번호 형식에 맞지 않습니다.")
	private String phoneNumber;
	
	@Past(message="오늘과 과거의 날짜만 지정 가능합니다.")
	private java.sql.Date hireDate;
	
	private String jobId;
	
	@Min(value=100, message="급여는 100보다 작을 수 없습니다.")
	private double salary;
	
	@DecimalMin(value="0.0", message="보너스율은 0보다 작을 수 없습니다.")
	@DecimalMax(value="0.99", message="보너스율은 0.99보다 클 수 없습니다.")
	private double commissionPct;
	
	private int managerId;
	
	private int departmentId;
	
	public EmpVO() {
		super();
	}
	public EmpVO(int employeeId, String firstName, String lastName, String email, String phoneNumber, Date hireDate,
			String jobId, double salary, double commissionPct, int managerId, int departmentId) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.hireDate = hireDate;
		this.jobId = jobId;
		this.salary = salary;
		this.commissionPct = commissionPct;
		this.managerId = managerId;
		this.departmentId = departmentId;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public java.sql.Date getHireDate() {
		return hireDate;
	}
	public void setHireDate(java.sql.Date hireDate) {
		this.hireDate = hireDate;
	}
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public double getCommissionPct() {
		return commissionPct;
	}
	public void setCommissionPct(double commissionPct) {
		this.commissionPct = commissionPct;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	@Override
	public String toString() {
		return "EmpVO [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", hireDate=" + hireDate + ", jobId=" + jobId + ", salary="
				+ salary + ", commissionPct=" + commissionPct + ", managerId=" + managerId + ", departmentId="
				+ departmentId + "]";
	}
	
	
}
