<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="baseURL" value="${fn:replace(pageContext.request.requestURL, pageContext.request.requestURI, pageContext.request.contextPath)}" />
<br>
<link rel="stylesheet" href="${baseURL}/foo.css" />
<script src="${baseURL}/"></script>
<a href="${baseURL}/menu">menu</a>
Copyright 2015 JuJa.com.ua (all rights reserved)
