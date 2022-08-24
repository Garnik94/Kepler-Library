<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DatabaseMetaData" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="service.dao.JdbcExamples.BatchExample" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 23.08.2022
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <%
        BatchExample batchExample = new BatchExample(application);
        batchExample.batchWithPreparedStatement();
        batchExample.batchWithStatement();
    %>

</body>
</html>
