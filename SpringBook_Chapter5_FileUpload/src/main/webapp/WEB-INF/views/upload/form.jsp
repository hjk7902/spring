<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>CoderBy</title>
</head>
<body>
<c:url var="actionURL" value='/upload/new' />
<form action="${actionURL}" method="post" enctype="multipart/form-data">
	<select name="dir">
		<option value="/">/
		<option value="/images">/이미지
		<option value="/data">/자료실
		<option value="/bigdata">/빅데이터
		<option value="/common">/공통
	</select>
	<input type="file" name="file">
	<input type="submit" value="SAVE">
	<input type="reset" value="CANCEL">
</form>
</body>
</html>