<%@ page import="java.sql.*" %><%--
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
        Connection connection = (Connection) application.getAttribute("dbConnection");
        connection.setAutoCommit(false);

        try {
            String query1 = "INSERT INTO Languages VALUES ('Arabic')";
            String query2 = "INSERT INTO Languages VALUES ('Georgian')";
            String query3 = "INSERT INTO Languages VALUES ('French'..............)";
            String query4 = "INSERT INTO Languages VALUES ('German')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            statement.executeUpdate(query3);
            statement.executeUpdate(query4);
            connection.commit();
      %>
            <h1>Transaction succeeded</h1>
      <%
        } catch (SQLException e){
            connection.rollback();
      %>
            <h1>Transaction failed</h1>
      <%
        }
      %>

</body>
</html>
