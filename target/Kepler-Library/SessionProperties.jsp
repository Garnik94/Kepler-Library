<%--
  Created by IntelliJ IDEA.
  User: garnik.haydosyan
  Date: 2022-08-18
  Time: 11:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
</head>
<body>

    <%= "session id - " + session.getId()%> <br><br>
    <%= "is new - " + session.isNew()%> <br><br>

    <%= session.getAttributeNames().nextElement()%>



</body>
</html>
