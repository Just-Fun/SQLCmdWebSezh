<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SQLcmd: help</title>
</head>
<body>
List command
<pre>
Существующие команды:

<a href="connect">connect|databaseName|userName|password</a>
для подключения к базе данных, с которой будем работать

<a href="tables">tables</a>
для получения списка всех таблиц базы, к которой подключились

clear|tableName
для очистки всей таблицы

create|tableName|column1|value1|column2|value2|...|columnN|valueN
для создания записи в таблице

find|tableName
для получения содержимого таблицы 'tableName'

<a href="help">help</a>
для вывода этого списка на экран

exit
для выхода из программы
</pre>
<%@ include file="/footer.jsp" %>
</body>
</html>
