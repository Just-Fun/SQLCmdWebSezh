<%@ page import="java.util.Set" %>
<%--
  Created by IntelliJ IDEA.
  User: max

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SQLcmd: tables</title>
</head>
<body>
<%
  Set<String> items= (Set<String>) request.getAttribute ("items");
    out.println("<table border='1'>");
  out.println ("<tr>");

  for(String item: items){
    out.println ("<tr><td><a href='find?tableName="+item+"'>"+item+"</a></td>"+
    "<td><a href='insert?tableName="+item+"'>Вставить</a></td>"+
    "<td><a href='clear?tableName="+item+"'>Очистить</a></td></tr>");
  }
  out.println ("</tr>");
out.println("</table>");

%>
<%@ include file="/footer.jsp" %>
</body>
</html>
*