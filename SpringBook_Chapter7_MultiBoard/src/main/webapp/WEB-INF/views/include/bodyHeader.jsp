<%@page import="com.example.myapp.board.service.BoardCategoryService"%>
<%@page import="java.util.List"%>
<%@page import="com.example.myapp.board.model.BoardCategory"%>
<%@page import="com.example.myapp.board.service.IBoardCategoryService"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page contentType="text/html; charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="i18n/header" />
<!-- HEADER -->
<div class="container">
<div id="divHeaderWrapper">
	<header class="header-standard-2">     
    <!-- MAIN NAV -->
    <div class="navbar navbar-wp navbar-arrow mega-nav" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <i class="fa fa-bars icon-custom"></i>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/>" title="">
            	<fmt:message key="TITLE"/>
            </a><div style="padding-left:15px; width:200px">http://www.javaspecialist.co.kr</div>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="hidden-md hidden-lg">
                    <div class="bg-light-gray">
                        <form class="form-horizontal form-light p-15" role="form">
                            <div class="input-group input-group-lg">
                                <input type="text" class="form-control" placeholder="I want to find ...">
                                <span class="input-group-btn">
                                    <button class="btn btn-white" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div>
                        </form>
                    </div>
                </li>
				<li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><strong><fmt:message key="BOARD"/></strong></a>
                    <ul class="dropdown-menu">
                    	<li><a href="<c:url value='/board/cat/1'/>">게시판</a>
			        	<li><a href="<c:url value='/board/cat/2'/>">자료실</a>
			        	<li><a href="<c:url value='/board/cat/3'/>">겔러리</a>
					</ul>
				</li>
                <li class="dropdown">
                    <a href='<c:url value="/member/login"/>' class="dropdown-toggle" data-toggle="dropdown"><strong><fmt:message key="MEMBER"/></strong></a>
                    <ul class="dropdown-menu">
                    	<li><a href="<c:url value='/member/login'/>"><fmt:message key="MY_INFO"/></a>
                        <li><a href="<c:url value='/member/update'/>"><fmt:message key="UPDATE_USER_INFO"/></a>
                        <li><a href="<c:url value='/member/delete'/>"><fmt:message key="EXIT_MEMBER"/></a>
                        <li><a href="<c:url value='/member/logout'/>"><fmt:message key="SIGN_OUT"/></a>
                        <li role="separator" class="divider"></li>
                        <li><a href="<c:url value='/member/insert'/>"><fmt:message key="JOIN_MEMBER"/></a>
                    </ul>
                </li>
                <li class="dropdown dropdown-aux animate-click" data-animate-in="animated" data-animate-out="animated fadeOutDown" style="z-index:500;">
                    <a href="#" class="dropdown-form-toggle" data-toggle="dropdown"><i class="fa fa-search"></i></a>
                    <ul class="dropdown-menu dropdown-menu-user">
                        <li id="dropdownForm">
                            <div class="dropdown-form">
                                <form class="form-horizontal form-light p-15" action="<c:url value='/board/search'/>" method="post" role="form">
                                    <div class="input-group">
                                        <input type="text" class="form-control" name="keyword" placeholder="키워드를 입력하세요.">
                                        <span class="input-group-btn">
                                            <input type="submit" class="btn btn-base" value="Go">
                                        </span>
                                    </div>
                                </form>
                            </div>
                        </li>
                    </ul>
                </li>
               <li><div>
               <c:if test="${empty email}">
               <br><a href="<c:url value="/member/login"/>" class="btn btn-danger"><fmt:message key="SIGN_IN"/></a>
               </c:if>
               <c:if test="${!empty email}">
               <br><a href="<c:url value='/member/login'/>" class="btn btn-danger"><fmt:message key="MY_INFO"/></a>
               </c:if>
               </div>
                </li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
	</header>
</div>
</div>