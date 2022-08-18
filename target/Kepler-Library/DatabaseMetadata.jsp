<%@ page import="service.dao.ShowDatabaseMetaData" %>
<%@ page import="java.sql.Connection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
</head>
<body>

    <%
        Connection connection = (Connection) application.getAttribute("dbConnection");
        ShowDatabaseMetaData sHowDatabaseMetaData = new ShowDatabaseMetaData(connection);
    %>

    <%= sHowDatabaseMetaData.getDriverName() %> <br><br>
    <%= sHowDatabaseMetaData.getDriverVersion() %> <br><br>
    <%= sHowDatabaseMetaData.getUserName() %> <br><br>
    <%= sHowDatabaseMetaData.getDatabaseProductName() %> <br><br>
    <%= sHowDatabaseMetaData.getDatabaseProductVersion() %>

</body>
</html>
