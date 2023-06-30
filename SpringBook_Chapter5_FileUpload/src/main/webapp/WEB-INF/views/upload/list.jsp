<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Example</title>
<script type="text/javascript">
window.onload = function() {
	var deleteButtons = document.querySelectorAll(".delete");
	for(var i=0; i<deleteButtons.length; i++) {
		deleteButtons[i].onclick = function() {
			if(confirm("이 작업은 되돌릴 수 없습니다. 파일을 삭제하겠습니까?")) {
				return true;
			}else {
				return false;
			}
		}
	}
}
</script>
</head>
<body>
<c:url var="actionURL" value='/upload/updateDir'/>
<form action="${actionURL}" method="post">
<table border="1">
<tr>
	<th>ID</th>
	<td>경로</td>
	<td>파일명</td>
	<td>크기</td>
	<td>유형</td>
	<td>날짜</td>
	<td>삭제</td>
</tr>
<c:forEach var="file" items="${fileList}">
<tr>
	<td><input type="checkbox" name="fileIds" value="${file.fileId}"> ${file.fileId}</td>
	<td>${file.directoryName}</td>
	<td>
		<c:set var="len" value="${fn:length(file.fileName)}"/>
		<c:set var="filetype" value="${fn:toUpperCase(fn:substring(file.fileName, len-4, len))}"/>
		<c:url var="link" value="/file/${file.fileId}"/>
		<c:if test="${(filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.PNG') or (filetype eq '.GIF')}">
			<img src="${link}" width="100" class="img-thumbnail"><br>
		</c:if>
		<c:if test="${!((filetype eq '.JPG') or (filetype eq 'JPEG') or (filetype eq '.PNG') or (filetype eq '.GIF'))}">
			<a href="${link}">${file.fileName}</a><br>
		</c:if>
	</td>
	<td>
		<fmt:formatNumber value="${file.fileSize/1024}" pattern="#,###"/>KB
	</td>
	<td>${file.fileContentType}</td>
	<td>${file.fileUploadDate}</td>
	<td>
		<c:url var="deletelink" value="/upload/delete/${file.fileId}"/>
		<a href="${deletelink}" class="delete">삭제</a>
	</td>
</tr>
</c:forEach>
</table>
선택한 파일을 <select name="directoryName">
	<option value="/">/
	<option value="/images">/이미지
	<option value="/data">/자료실
	<option value="/bigdata">/빅데이터
	<option value="/common">/공통
</select>(으)로 <input type="submit" value="이동"><p>
<a href='<c:url value="/upload/new"/>'>업로드</a>
<a href='<c:url value="/upload"/>'>처음으로</a>
</form>
</body>
</html>