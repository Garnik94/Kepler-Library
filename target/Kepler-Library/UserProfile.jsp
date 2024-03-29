<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body>

<div style="float: right; text-align: center; width: 300px">
    <span class="textStyle">
        <jsp:useBean id="CurrentUser" class="model.User" scope="session"/>
        <jsp:getProperty name="CurrentUser" property="username"/>

<%--        <jsp:setProperty name="CurrentUser" property="*"/>--%>
<%--        <jsp:setProperty name="CurrentUser" property="username"/>--%>
    </span> <br><br>
<%--    <form action="books" method="get">--%>
    <form action="BookSection.jsp" method="get">
        <input class="navigationButtons" type="submit" value="Home">
    </form>

    <%
        User user = (User) session.getAttribute("CurrentUser");
        if (user.hasEditPermission() == 1) {
    %>
            <form action="PermittedProfile.jsp" method="get">
                <input class="navigationButtons" type="submit" value="Manage">
            </form>
    <% } %>

    <form action="logout" method="post">
        <input class="navigationButtons" type="submit" value="Logout">
    </form>
</div>

</body>
</html>
