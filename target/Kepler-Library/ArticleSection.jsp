<%@ page import="model.User" %>
<%@ page import="service.dao.UserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.

    if (session.getAttribute("username") == null || session.getAttribute("password") == null) {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

<%
    User user = UserDAO.getUser(new User((String) session.getAttribute("username")));
%>

<span class="userWelcomeMessage">
        <%=
        "Hi " + session.getAttribute("username")
        %>
    </span>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
<% if (user.isHasEditPermission() == 1) { %>
        <form action="AdminProfile.jsp" method="get">
            <input type="submit" value="Admin profile">
        </form>
<% } %>

<div class="bigContainer">
    <span>Search</span><br>
    <div class="bigContainer">
        <label>
            <span>Search</span><br>
            <input class="inputAreaStyle" type="text">
        </label>
    </div>

    <span>Search</span><br>
    <label>Search<br>
        <input type="checkbox">
    </label>

    <div class="contentContainer">
        <%

        %>
        <div class="contentWindow"></div>
    </div>

    <a href="bookSection?page=1">1</a>
    <a href="bookSection?page=2">2</a>
    <a href="bookSection?page=3">3</a>

</div>

</body>
</html>
