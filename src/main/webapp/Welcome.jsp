<%@ page import="service.dao.UserDAO" %>
<%@ page import="model.User" %>
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

    <span class="userWelcomeMessage">
        <%=
        "Hi " + session.getAttribute("username")
        %>
    </span>
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
    <%
        User user = UserDAO.getUser(new User((String) session.getAttribute("username")));

        if (user.isHasEditPermission() == 1) {
    %>
    <form action="AdminProfile.jsp">
        <input type="submit" value="Admin profile">
    </form>
    <%
        }
    %>

    <span class="textStyle">Choose section</span><br>
    <form>
        <input class="bookSectionButtonStyle" formaction="books" formmethod="post" type="submit" value="Books"><br>
        <input class="articleSectionButtonStyle" formaction="articles" formmethod="post" type="submit" value="Articles"><br>
    </form>
</body>
</html>
