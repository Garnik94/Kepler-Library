<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DatabaseMetaData" %><%--
  Created by IntelliJ IDEA.
  User: garnik.haydosyan
  Date: 2022-08-23
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

  <%
      Connection connection = (Connection) application.getAttribute("dbConnection");
      DatabaseMetaData databaseMetaData = connection.getMetaData();
  %>

  <%= "getDriverName() - " + databaseMetaData.getDriverName() %> <br><br>
  <%= "getDriverVersion() - " + databaseMetaData.getDriverVersion() %> <br><br>
  <%= "getUserName() - " + databaseMetaData.getUserName() %> <br><br>
  <%= "getDatabaseProductName() - " + databaseMetaData.getDatabaseProductName() %> <br><br>
  <%= "getDatabaseProductVersion() - " + databaseMetaData.getDatabaseProductVersion() %>

</body>
</html>
