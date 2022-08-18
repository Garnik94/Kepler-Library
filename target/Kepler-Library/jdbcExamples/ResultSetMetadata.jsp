<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.google.common.base.Optional" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %><%--
  Created by IntelliJ IDEA.
  User: garnik.haydosyan
  Date: 2022-08-18
  Time: 4:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="../graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
</head>
<body>

    <%
        String query = "SELECT * FROM Books";
        Connection connection = ((Optional<Connection>) application.getAttribute("dbConnection")).get();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
    %>

    <%="getColumnCount() - " + resultSetMetaData.getColumnCount()%> <br><br>
    <%="getColumnName(2) - " + resultSetMetaData.getColumnName(2)%> <br><br>
    <%="getColumnType(3) - " + resultSetMetaData.getColumnType(3)%> <br><br>
    <%="getTableName(1) - " + resultSetMetaData.getTableName(2)%>

</body>
</html>
