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
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body>

<%
    User user = UserDAO.getUser(new User((String) session.getAttribute("username")));
%>

<h1>
    <%= request.getParameter("page")%>
</h1>

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
    <div class="bigContainer">
        <label>
            <span>Search</span><br>
            <input class="inputAreaStyle" type="text">
        </label>
    </div>

    <label>Search<br>
        <input type="checkbox">
    </label>

    <div class="contentContainer">
        <%

        %>
        <div class="contentWindow"></div>
    </div>

    <form>
        <%
            for (int i = 1; i < 10; i++) {
                String url = "books?page=" + i;
        %>
        <a href="<%=url%>"><%=i%>
        </a>
        <%
            }
        %>
    </form>

</div>

</body>
</html>