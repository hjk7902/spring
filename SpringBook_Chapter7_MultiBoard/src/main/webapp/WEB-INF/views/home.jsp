<%@ page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="i18n/header"/>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/include/staticFiles.jsp"/>
<body>
<jsp:include page="/WEB-INF/views/include/bodyHeader.jsp"/>
<div class="container">
	<div class="pg-opt">
        <div class="row">
            <div class="col-md-6 pc">
                <h2><fmt:message key="HOME"/></h2>
            </div>
            <div class="col-md-6">
                <ol class="breadcrumb">
                    <li><fmt:message key="DASHBOARD"/></li>
                    <li class="active"><fmt:message key="HOME"/></li>
                </ol>
            </div>
        </div>
    </div>

	<div class="content">
	<div class="alert alert-warning" class="page-header">
		<h3><fmt:message key="WELCOME_MESSAGE"/></h3>
	</div>
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">
					<a href="board/cat/1">카테고리 1번 게시판 리스트</a><br>
					<a href="board/cat/2">카테고리 2번 게시판 리스트</a><br>
					<a href="board/cat/3">카테고리 3번 게시판 리스트</a><br>
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">Curabitur
					sed bibendum neque, at congue ipsum. Lorem ipsum dolor samet,
					consectetur adipiscing elit. Vivamus mattis a mauris.</div>
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-4">Integer
					commodo euismod accumsan. Mauris bibendum ante at aliquet, eu
					dictum orci porttitor. Mauris cursus cursus.</div>
			</div>
			<div class="progress">
		<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
		<span class="sr-only"></span>
		</div>
	</div>
	<div class="progress">
		<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%;">
		<span class="sr-only"></span>
		</div>
	</div>
	<div class="alert alert-info">
		<ol>
			<li>Donec vitae suscipit leo. Mauris arcu felis, eleifend id porta.
		</ol>
	</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"/>
</body>
</html>