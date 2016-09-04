<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>SQLCmd</title>
    </head>
    <body>

    <h2>Creating new database</h2>
    <form:form method="POST" action="creatingDatabase">
       <table>
        <tr>
            <td><form:label path="name">Database name</form:label></td>
            <td><form:input path="name" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>
    </form:form>
    <%@include file="footer.jsp" %>
    </body>
</html>