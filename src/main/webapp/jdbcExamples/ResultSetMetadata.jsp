<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@ page import="service.dao.JdbcExamples.ResultSetMetadataExample" %><%--
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
      ResultSetMetadataExample resultSetMetadataExample = new ResultSetMetadataExample(application);
  %>

  <%="getColumnCount() - " + resultSetMetadataExample.getColumnCount()%> <br><br>
  <%="getColumnName(2) - " + resultSetMetadataExample.getColumnName(2)%> <br><br>
  <%="getColumnType(3) - " + resultSetMetadataExample.getColumnType(3)%> <br><br>

</body>
</html>
