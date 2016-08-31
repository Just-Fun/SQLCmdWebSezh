<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>SQLCmd: connect</title>
</head>
<body>
<form action="connect" method="post">
  <table>
    <tr>
      <td>Database name</td>
      <td><input type="text" name="dbname" id="dbname"/></td>
    </tr>
    <tr>
      <td>User name</td>
      <td><input type="text" name="username" id="username"/></td>
    </tr>
    <tr>
      <td>Password</td>
      <td><input type="password" name="password" id="password"/></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" value="connect" id="connect"/></td>
    </tr>
  </table>
</form>
<%@ include file="/footer.jsp" %>
</body>
</html>
