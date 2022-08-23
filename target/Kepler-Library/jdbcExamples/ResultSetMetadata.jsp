<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %><%--
  Created by IntelliJ IDEA.
  User: garnik.haydosyan
  Date: 2022-08-23
  Time: 6:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

  <%
      Connection connection =  (Connection) application.getAttribute("dbConnection");
      String query = "SELECT * FROM Books";
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
  %>

  <%="getColumnCount() - " + resultSetMetaData.getColumnCount()%> <br><br>
  <%="getColumnName(2) - " + resultSetMetaData.getColumnName(2)%> <br><br>
  <%="getColumnType(3) - " + resultSetMetaData.getColumnType(2)%> <br><br>

</body>
</html>
