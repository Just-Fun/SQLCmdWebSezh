<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>SQLCmd</title>
    </head>
    <body>
        <table border="1">
        <tr>
            <td>action</td>
            <td>id</td>
            <td>userName</td>
            <td>dbName</td>
        </tr>
        <tr>

        </tr>
            <c:forEach items="${allActions}" var="userAction">
                <tr>
                    <td>${userAction.action}</td>
                    <td>${userAction.id}</td>
                    <td>${userAction.connection.userName}</td>
                    <td>${userAction.connection.dbName}</td>
                     <%-- Comment --%>
                </tr>
            </c:forEach>
        </table>
        <%@include file="footer.jsp" %>
    </body>
</html>