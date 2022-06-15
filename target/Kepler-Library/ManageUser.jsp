<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.

    if (session.getAttribute("CurrentUser") == null) {
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
<body class="standardPageBody">

<span class="userWelcomeMessage">
        <%=
        "Hi " + session.getAttribute("CurrentUser")
        %>
</span>

<form action="welcome" method="get">
    <input type="submit" value="Home">
</form>

<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>

<form action="searchManageableUser" method="get">
    <label>Search user<br>
        <input type="text" name="searchingUser">
    </label>
    <input type="submit" value="Search">
</form>

<%
    if (session.getAttribute("ManageableUser") != null) {
%>

<p><%= (User) (session.getAttribute("ManageableUser"))%></p>

<form action="permitUser" method="post">
    <input type="submit" value="Permit user">
</form>

<form action="forbidUser" method="post">
    <input type="submit" value="Forbid user">
</form>

<form action="deleteUser" method="post">
    <input type="submit" value="Delete user">
</form>

<%
    }
%>

<%
    if (session.getAttribute("searchingUserNotFound") != null &&
            session.getAttribute("searchingUserNotFound").equals("true")) {
%>

<span class="errorMessageStyle">User is not found</span>

<%
    }
%>

</body>
</html>
