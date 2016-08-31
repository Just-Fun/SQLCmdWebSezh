<%@ page import="ua.com.juja.sqlcmd.model.DataSet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%--
  Created by IntelliJ IDEA.
  User: max
  Date: 06.11.2015
  Time: 23:16

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SQLCmd: menu</title>
</head>
<body>
<%
        List<DataSet> item= (List<DataSet>)request.getAttribute ("items");
%>

<form action="update" method="post">

    <table>
        <tr><td>Table Name</td><td>
            <input readonly="true" type="text" name="tableName" value="<%= request.getParameter("tableName")%>">
        </td></tr>
        <%
            Set<String> columName= (Set<String>) request.getAttribute ("columName");
            DataSet i=item.get(0);
            for(String column: columName){
        %>
        <tr>
            <td>name</td>
            <td><input readonly="true" type="text" name="name[]" value="<%=column%>"/></td>
            <td>value</td>
            <td><input type="text" name="value[]" value="<%=i.get(column)%>"/></td>
        </tr>
        <%
            }
        %>
    </table>
    <button type="submit">send</button>
</form>
<%@ include file="/footer.jsp" %>
</body>
</html>
