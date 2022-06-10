<%@ page import="service.dao.UserDAO" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.

    if (session.getAttribute("CurrentUser") == null /*|| session.getAttribute("password") == null*/) {
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
        User user = (User) session.getAttribute("CurrentUser");
    %>

    <span class="userWelcomeMessage">
        <%=
        "Hi " + user
        %>
    </span>

    <form action="Welcome.jsp">
        <input type="submit" value="Home">
    </form>

    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
    <%
        if (user.isHasEditPermission() == 1) {
    %>
    <form action="AdminProfile.jsp">
        <input type="submit" value="Admin profile">
    </form>
    <%
        }
    %>

    <%
        if (user.getId() != 1) {
    %>
    <form action="AdminProfile.jsp">
        <input type="submit" value="Delete my profile">
    </form>
    <%
        }
    %>

    <span class="textStyle">Choose section</span><br>
    <form>
        <input class="bookSectionButtonStyle" formaction="BookSection.jsp" formmethod="get" type="submit" value="Books"><br>
        <input class="articleSectionButtonStyle" formaction="ArticleSection.jsp" formmethod="get" type="submit" value="Articles"><br>
    </form>
</body>
</html>
