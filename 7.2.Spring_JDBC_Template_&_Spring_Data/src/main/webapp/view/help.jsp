<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>SQLCmd</title>
    </head>
     <body>
       Существующие команды:<br>
           <a href="connect">connect</a><br>
               для подключения к базе данных, с которой будем работать<br>
           <a href="list">list</a><br>
               для получения списка всех таблиц базы, к которой подключились<br>
           <a href="databases">databases</a><br>
               для получения списка всех databases<br>
           <a href="createDatabase">createDatabase</a><br>
               для создания новой базы<br>
           <a href="student">student</a><br>
               для создания студентов :)<br>
           <a href="students">students</a><br>
              для получения списка students<br>
           clear|tableName<br>
               для очистки всей таблицы<br>
           <a href="help">help</a><br>
               для вывода этого списка на экран<br>
           <a href="actions/postgres">actions</a><br>
               для вывода списка действий пользователя postgres<br>
           exit<br>
               для выхода из программы<br>
           <%@include file="footer.jsp" %>
       </body>
</html>