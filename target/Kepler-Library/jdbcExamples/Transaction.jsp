<%@ page import="java.sql.*" %>
<%@ page import="service.dao.JdbcExamples.TransactionExample" %><%--
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
          String parameter = request.getParameter("failed");
          TransactionExample transactionExample = new TransactionExample(application);

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

</body>
</html>
