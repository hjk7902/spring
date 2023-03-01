<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>데이터베이스 정보</h1>
<c:forEach var="data" items="${sampleList}">
<p>id: ${data.id}, name: ${data.name}, email: ${data.email}</p> 
</c:forEach>
</body>
</html>