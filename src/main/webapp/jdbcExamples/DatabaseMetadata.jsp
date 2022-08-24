<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DatabaseMetaData" %>
<%@ page import="service.dao.JdbcExamples.DatabaseMetadataExample" %><%--
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
      DatabaseMetadataExample databaseMetadataExample = new DatabaseMetadataExample(application);
  %>

  <%= "getDriverName() - " + databaseMetadataExample.getDriverName() %> <br><br>
  <%= "getDriverVersion() - " + databaseMetadataExample.getDriverVersion() %> <br><br>
  <%= "getUserName() - " + databaseMetadataExample.getUserName() %> <br><br>
  <%= "getDatabaseProductName() - " + databaseMetadataExample.getDatabaseProductName() %> <br><br>
  <%= "getDatabaseProductVersion() - " + databaseMetadataExample.getDatabaseProductVersion() %>

</body>
</html>
