<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <head>
        <title>SQLCmd</title>
        <script src="${ctx}/resources/js/jquery-2.2.3.js"></script>
        <script src="${ctx}/resources/js/jquery.tmpl.js"></script>
        <script src="${ctx}/resources/js/main.js"></script>
        <script>
            $(window).load(function () {
                init('${ctx}');
            });
        </script>
    </head>
    <body>
        <div id="loading" style="display:none">Loading...</div>
        <%@include file="login.jsp"%>
        <%@include file="serverMenu.jsp"%>
        <%@include file="databases.jsp"%>
        <%@include file="createDatabase.jsp"%>
        <%@include file="dropDatabase.jsp"%>
        <%@include file="actions.jsp"%>
        <%@include file="databaseMenu.jsp"%>
        <%@include file="clearTable.jsp"%>
        <%@include file="createRecord.jsp"%>
        <%@include file="createTable.jsp"%>
        <%@include file="dropTable.jsp"%>
        <%@include file="table.jsp"%>
        <%@include file="update.jsp"%>
    </body>
</html>
