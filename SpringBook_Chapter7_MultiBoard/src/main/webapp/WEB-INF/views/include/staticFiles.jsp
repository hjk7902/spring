<%@ page contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n/header" />
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="robots" content="index, follow">
    
    <title><fmt:message key="TITLE"/></title>

    <link href="<c:url value='/favicon.png'/>" rel="icon" type="image/png">
	
	<link rel="stylesheet" href="<c:url value='/css/default.css'/>">
    
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/global-style.css'/>" media="screen">  

    <script src="<c:url value='/js/jquery-3.6.3.js'/>"></script>
    <script src="<c:url value='/js/bootstrap.js'/>"></script>
</head>