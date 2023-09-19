<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="i18n/board"/>
<!DOCTYPE html> 
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
	<div class="pg-opt">
        <div class="row">
            <div class="col-md-6 pc">
                <h2><fmt:message key="UPDATE_ARTICLE"/></h2>
            </div>
            <div class="col-md-6">
                <ol class="breadcrumb">
                    <li><fmt:message key="BOARD"/></li>
                    <li class="active"><fmt:message key="UPDATE_ARTICLE"/></li>
                </ol>
            </div>
        </div>
    </div>
	<div class="content">
	<form action="<c:url value='/board/update'/>" method="post" enctype="multipart/form-data" class="form-horizontal">
	<c:if test="${!empty categoryList}">
	<div class="form-group">
      <label class="control-label col-sm-2" for="categoryId"><fmt:message key="CATEGORY"/></label>
      <div class="col-sm-4">
        <select name="categoryId" id="categoryId" class="form-control" required>
        	<c:forEach var="category" items="${categoryList}">
        	<option value="${category.categoryId}" ${category.categoryId eq board.categoryId ? "selected" : ""}>${category.categoryName}</option>
        	</c:forEach>
        </select>
      </div>
    </div>
    </c:if>
	<div class="form-group">
      <label class="control-label col-sm-2" for="writer"><fmt:message key="WRITER"/></label>
      <div class="col-sm-2">
        <input type="text" name="writer" id="writer" class="form-control" value="${board.writer}" readonly>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="email"><fmt:message key="EMAIL"/></label>
      <div class="col-sm-4">
        <input type="text" name="email" id="email" class="form-control" value="${board.email}" autocomplete="off" required readonly>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="password"><fmt:message key="PASSWORD"/></label>
      <div class="col-sm-2">
        <input type="password" name="password" id="password" class="form-control" required>
      </div>${passwordError}
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="title"><fmt:message key="TITLE"/></label>
      <div class="col-sm-8">
        <input type="text" name="title" id="title" class="form-control" value="${board.title}" required>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="content"><fmt:message key="CONTENT"/></label>
      <div class="col-sm-8">
        <textarea name="content" id="content" rows="15" cols="100" class="form-control">${board.content}</textarea>
      </div>
    </div>
    <!-- c:if test="${!empty userid}"-->
    <div class="form-group">
      <label class="control-label col-sm-2" for="file"><fmt:message key="FILE"/></label>
      <div class="col-sm-8">
      	<input type="hidden" name="fileId" value="${board.fileId}">
        <input type="file" id="file" name="file">${board.fileName}
      </div>
    </div>
    <!-- /c:if-->
    <div class="form-group">
    	<div class="col-sm-offset-2 col-sm-8">
			<input type="hidden" name="boardId" value="${board.boardId}">
			<input type="hidden" name="masterId" value="${board.masterId}">
			<input type="hidden" name="replyNumber" value="${board.replyNumber}">
			<input type="hidden" name="replyStep" value="${board.replyStep}">
			<input type="submit" class="btn btn-info" value="<fmt:message key="UPDATE"/>">
			<input type="reset" class="btn btn-info" value="<fmt:message key="CANCEL"/>">
		</div>
	</div>
	</form>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>