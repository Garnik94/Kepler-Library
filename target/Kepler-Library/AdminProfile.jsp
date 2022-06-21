<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.

    if (session.getAttribute("CurrentUser") == null) {
        response.sendRedirect("Login.jsp");
        return;
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
<body class="standardPageBody">

<div style="float: right; text-align: center; width: 300px">
    <span class="textStyle">
        ${CurrentUser}
    </span> <br> <br>

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

<br><br><br><br><br><br><br><br><br><br><br>

<form>
    <input class="loginButtonStyle" type="submit" formaction="AddBook.jsp" formmethod="get" value="Add new book"><br>
    <input class="loginButtonStyle" type="submit" formaction="ManageUser.jsp" formmethod="get" value="Manage user">
</form>
</body>
</html>
