<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    <head>
        <title>SQLCmd</title>
        <script type="text/javascript" src="${ctx}/resources/js/jquery-2.1.4.js"></script>
        <script type="text/javascript" src="${ctx}/resources/js/helpFun.js"></script>
    </head>
    <body>
        <div id="help_fun">
            <div id="loading">Loading...</div>
        </div>

    Существующие команды:<br>
        connect|databaseName|userName|password<br>
            для подключения к базе данных, с которой будем работать<br>
        tables<br>
            для получения списка всех таблиц базы, к которой подключились<br>
        clear|tableName<br>
            для очистки всей таблицы<br>
        create|tableName|column1|value1|column2|value2|...|columnN|valueN<br>
            для создания записи в таблице<br>
        find|tableName<br>
            для получения содержимого таблицы tableName<br>
        help<br>
            для вывода этого списка на экран<br>
        exit<br>
            для выхода из программы<br>
        <%@include file="footer.jsp" %>
    </body>
</html>