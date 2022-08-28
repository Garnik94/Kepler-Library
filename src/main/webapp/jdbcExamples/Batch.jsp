<%@ page import="service.dao.JdbcExamples.BatchExample" %>
<%@ page import="javax.sql.RowSet" %>
<%@ page import="javax.sql.rowset.JdbcRowSet" %>
<%@ page import="com.sun.rowset.JdbcRowSetImpl" %>
<%@ page import="javax.sql.rowset.RowSetProvider" %>
<%@ page import="com.sun.rowset.CachedRowSetImpl" %>
<%@ page import="com.sun.rowset.WebRowSetImpl" %>
<%@ page import="java.sql.*" %>
<%@ page import="service.dao.JdbcExamples.RowsetExamples.CachedRowSetExample" %><%--
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
//        batchExample.batchWithStatement();
    %>

</body>
</html>
