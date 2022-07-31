<%@ page import="model.User" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 30.07.2022
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<div style="float: right; text-align: center; width: 300px">
    <span class="textStyle">
        <jsp:useBean id="CurrentUser" type="model.User" scope="session">
            <jsp:getProperty name="CurrentUser" property="username"/>
        </jsp:useBean>
<%--        ${CurrentUser}--%>
    </span> <br><br>

    <form action="welcome" method="get">
        <input class="navigationButtons" type="submit" value="Home">
    </form>

    <%
        User user = (User) session.getAttribute("CurrentUser");
        if (user.isHasEditPermission() == 1) { %>
    <form action="AdminProfile.jsp" method="get">
        <input class="navigationButtons" type="submit" value="Manage">
    </form>
    <% } %>

    <form action="logout" method="post">
        <input class="navigationButtons" type="submit" value="Logout">
    </form>
</div>

</body>
</html>
