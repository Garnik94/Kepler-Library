<%@ page import="java.sql.*" %>
<%@ page import="service.dao.JdbcExamples.TransactionExample" %>
<%@ page import="service.dao.JdbcExamples.BatchExample" %>
<%@ page import="service.dao.JdbcExamples.DatabaseMetadataExample" %>
<%@ page import="service.dao.JdbcExamples.ResultSetMetadataExample" %><%--
  Created by IntelliJ IDEA.
  User: garnik.haydosyan
  Date: 2022-08-23
  Time: 6:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

        <%
            String example = request.getParameter("example");
            if (example.equals("batch")){
                BatchExample batchExample = new BatchExample(application);
                batchExample.batchWithPreparedStatement();
//        batchExample.batchWithStatement();
        %>
            <h1>Batch completed</h1>
        <%
            } else if (example.equals("dbMetadata")){
                DatabaseMetadataExample databaseMetadataExample = new DatabaseMetadataExample(application);
        %>
            <p><%= databaseMetadataExample.getDriverName() %></p>
            <p><%= databaseMetadataExample.getDriverVersion() %></p>
            <p><%= databaseMetadataExample.getUserName() %></p>
            <p><%= databaseMetadataExample.getDatabaseProductName() %> </p>
            <p><%= databaseMetadataExample.getDatabaseProductVersion() %></p>
        <%
            } else if (example.equals("rsMetadata")) {
                ResultSetMetadataExample resultSetMetadataExample = new ResultSetMetadataExample(application);
        %>
            <p><%= resultSetMetadataExample.getColumnCount()%> </p>
            <p><%= resultSetMetadataExample.getColumnName(2)%> </p>
            <p><%= resultSetMetadataExample.getColumnType(3)%> </p>
        <%
            } else if (example.equals("transaction")) {
                String parameter = request.getParameter("failed");
                TransactionExample transactionExample = new TransactionExample(application);
        %>
                <%
                    if (parameter.equals("yes")) {
                        transactionExample.transactionFailed();
                %>
                        <h1>Transaction failed</h1>
                <%
                    } else if (parameter.equals("no")){
                    transactionExample.transactionSucceeded();
                %>
                        <h1>Transaction succeeded</h1>
                <%
                    }
                %>
        <%
            }
        %>

</body>
</html>
