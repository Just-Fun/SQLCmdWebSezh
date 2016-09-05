<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>SQLCmd</title>
    </head>
    <body>
    <h4>Таблицы:</h4>
        <c:forEach items="${list}" var="name">
            <a href="tables/${name}">${name}</a><br>
        </c:forEach>
        <%@include file="footer.jsp" %>
    </body>
</html>