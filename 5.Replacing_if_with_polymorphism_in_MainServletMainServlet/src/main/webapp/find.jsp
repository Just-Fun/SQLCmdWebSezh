<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find table: ${tableName}></title>
</head>
<body>
<%

  Set<String> itemsHead= (Set<String>) request.getAttribute ("itemsHead");
  List<DataSet> items= (List<DataSet>) request.getAttribute ("items");
  String tableName = request.getParameter("tableName");
out.println("<table border='1'>");
  out.println ("<tr>");
  for(String item: itemsHead){
    out.println ("<th>"+item+"</th>");
  }
  out.println ("</tr>");
  for(DataSet item: items) {
    out.println("<tr>");
    String id="";
    for (String itemHead : itemsHead) {
              if(itemHead.equals("id"))
                id= (String) item.get(itemHead);

              out.println("<td> "+ item.get(itemHead) + "</td>");

    }

    out.println("<td><a href='update?tableName="+tableName+"&id="+id+"'>update</a></td>");
    out.println("</tr>");
  }
  out.println("</table>");

  out.println("<a href='insert?tableName="+request.getParameter("tableName")+"'>Вставить</a>");
%>
!
<br>
<%@ include file="/footer.jsp" %>
</body>
</html>
