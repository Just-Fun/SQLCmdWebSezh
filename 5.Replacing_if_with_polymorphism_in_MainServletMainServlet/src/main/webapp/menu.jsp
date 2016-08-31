<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>SQLCmd: menu</title>
</head>
<body>

<%
List<String> items= (List<String>) request.getAttribute ("items");

  for(String item: items){
  out.println ("<a href='"+item+"'>"+item+"</a><br>");
}
  %>
</body>
</html>