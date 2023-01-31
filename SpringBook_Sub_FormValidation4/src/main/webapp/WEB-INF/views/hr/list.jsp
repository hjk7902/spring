<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoderBy</title>
</head>
<body>
<h1>사원 목록</h1>
${message}
<a href="<c:url value='/hr/insert'/>">신규 사원 정보 입력</a>
<table border="1">
<tr>
	<th>EMPLOYEE_ID</th>
	<th>FIRST_NAME</th>
	<th>LAST_NAME</th>
	<th>EMAIL</th>
	<th>PHONE_NUMBER</th>
	<th>HIRE_DATE</th>
	<th>JOB_ID</th>
	<th>SALARY</th>
	<th>COMMISSION_PCT</th>
	<th>MANAGER_ID</th>
	<th>DEPARTMENT_ID</th>
</tr>
<c:forEach var="emp" items="${empList}">
<tr>
	<td><a href="<c:url value='/hr/${emp.employeeId}'/>">${emp.employeeId}</a></td>
	<td>${emp.firstName}</td>
	<td>${emp.lastName}</td>
	<td>${emp.email}</td>
	<td>${emp.phoneNumber}</td>
	<td>${emp.hireDate}</td>
	<td>${emp.jobId}</td>
	<td>${emp.salary}</td>
	<td>${emp.commissionPct}</td>
	<td>${emp.managerId}</td>
	<td>${emp.departmentId}</td>
</tr>
</c:forEach>
</table>
</body>
</html>