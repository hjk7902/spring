<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoderBy</title>
</head>
<body>
<h1>사원 정보 상세 조회</h1>
${message} <!-- 리다이렉트 메시지 출력 -->
<table border="1">
<tr>
	<th>EMPLOYEE_ID</th>
	<td>${emp.employeeId}</td>
</tr>
<tr>
	<th>FIRST_NAME</th>
	<td>${emp.firstName}</td>
</tr>
<tr>
	<th>LAST_NAME</th>
	<td>${emp.lastName}</td>
</tr>
<tr>
	<th>EMAIL</th>
	<td>${emp.email}</td>
</tr>
<tr>
	<th>PHONE_NUMBER</th>
	<td>${emp.phoneNumber}</td>
</tr>
<tr>
	<th>HIRE_DATE</th>
	<td>${emp.hireDate}</td>
</tr>
<tr>
	<th>JOB_ID</th>
	<td>${emp.jobId}</td>
</tr>
<tr>
	<th>SALARY</th>
	<td>${emp.salary}</td>
</tr>
<tr>
	<th>COMMISSION_PCT</th>
	<td>${emp.commissionPct}</td>
</tr>
<tr>
	<th>MANAGER_ID</th>
	<td>${emp.managerId}</td>
</tr>
<tr>
	<th>DEPARTMENT_ID</th>
	<td>${emp.departmentId}</td>
</tr>
</table>
<a href="update?empid=${emp.employeeId}">수정하기</a> 
<a href="delete?empid=${emp.employeeId}">삭제하기</a>
</body>
</html>