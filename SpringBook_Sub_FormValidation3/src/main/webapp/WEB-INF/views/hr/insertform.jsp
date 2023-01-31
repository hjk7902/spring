<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CoderBy</title>
</head>
<body>
<h1>사원정보 입력</h1>
<!-- form action="./insert" method="post"-->
<form:form action="./insert" method="post" modelAttribute="emp">
<table border="1">
<tr>
	<th>EMPLOYEE_ID</th>
	<td>
		<!-- input type="number" name="employeeId" required-->
		<form:input path="employeeId" required="required"/><form:errors path="employeeId"/>
	</td>
</tr>
<tr>
	<th>FIRST_NAME</th>
	<td>
		<!--input type="text" name="firstName"-->
		<form:input path="firstName"/><form:errors path="firstName"/>
	</td>
</tr>
<tr>
	<th>LAST_NAME</th>
	<td>
		<!-- input type="text" name="lastName" required-->
		<form:input path="lastName" required="required"/><form:errors path="lastName"/>
	</td>
</tr>
<tr>
	<th>EMAIL</th>
	<td>
		<!-- input type="text" name="email" required-->
		<form:input path="email" required="required"/><form:errors path="email"/>
	</td>
</tr>
<tr>
	<th>PHONE_NUMBER</th>
	<td>
		<!-- input type="text" name="phoneNumber"-->
		<form:input path="phoneNumber"/><form:errors path="phoneNumber"/>
	</td>
</tr>
<tr>
	<th>HIRE_DATE</th>
	<td>
		<!-- input type="date" name="hireDate" required-->
		<form:input path="hireDate" type="date"/><form:errors path="hireDate"/>
	</td>
</tr>
<tr>
	<th>JOB_ID</th>
	<td>
		<!-- select name="jobId">
		<c:forEach var="job" items="${jobList}">
			<option value="${job.jobId}">${job.jobTitle}</option>
		</c:forEach>
		</select-->
		<form:select path="jobId">
		<c:forEach var="job" items="${jobList}">
			<form:option label="${job.jobTitle}" value="${job.jobId}"/>
		</c:forEach>
		</form:select>
	</td>
</tr>
<tr>
	<th>SALARY</th>
	<td>
		<!-- input type="number" name="salary"-->
		<form:input path="salary" type="number"/><form:errors path="salary"/>
	</td>
</tr>
<tr>
	<th>COMMISSION_PCT</th>
	<td>
		<!-- input type="number" name="commissionPct" step="0.1" min="0" max="0.99"-->
		<form:input path="commissionPct" type="number" step="0.1" min="0" max="0.99"/><form:errors path="commissionPct"/>
	</td>
</tr>
<tr>
	<th>MANAGER_ID</th>
	<td>
		<!-- select name="managerId">
		<c:forEach var="manager" items="${managerList}">
			<option value="${manager.managerId}">${manager.firstName}</option>
		</c:forEach>
		</select-->
		<form:select path="managerId">
		<c:forEach var="manager" items="${managerList}">
			<form:option label="${manager.firstName}" value="${manager.managerId}"></form:option>
		</c:forEach>
		</form:select>
	</td>
</tr>
<tr>
	<th>DEPARTMENT_ID</th>
	<td>
		<!-- select name="departmentId">
		<c:forEach var="department" items="${deptList}">
			<option value="${department.departmentId}">${department.departmentName}</option>
		</c:forEach>
		</select-->
		<form:select path="departmentId">
		<c:forEach var="department" items="${deptList}">
			<form:option label="${department.departmentName}" value="${department.departmentId}"></form:option>
		</c:forEach>
		</form:select>
	</td>
</tr>
<tr>
	<th>&nbsp;</th>
	<td>
		<input type="submit" value="저장"> 
		<input type="reset" value="취소">
	</td>
</tr>
</table>
<!-- /form-->
</form:form>
</body>
</html>