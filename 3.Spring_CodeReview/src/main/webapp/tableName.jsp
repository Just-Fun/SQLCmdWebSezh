<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>sqlcmd</title>
</head>
  <body>
    <form action="${actionURL}" method="post">
      <table>
        <tr>
          <td>Table name</td>
          <td><label>
            <input type="text" name="tableName"/>
          </label></td>
        </tr>

        <tr>
          <td></td>
          <td><input type="submit" value="OK"/></td>
        </tr>
      </table>
    </form>
  </body>
</html>