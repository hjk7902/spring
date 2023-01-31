<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>CoderBy</title>
</head>
<body>
<h1>에러페이지</h1>
<p>애플리케이션에 오류가 발생했습니다. 담당자에게 문의하세요.</p>
<!--
	Failed URL: ${url}
	Exception:  ${exception.message}
	<c:forEach items="${exception.stackTrace}" var="ste">    ${ste} 
	</c:forEach>
-->
</body>
</html>