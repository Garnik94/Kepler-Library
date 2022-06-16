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

<form action="searchManageableUser" method="get">
    <label class="textStyle">Search user<br>
        <input class="inputAreaStyle" type="text" name="searchingUser">
    </label><br>
    <input class="searchButton" type="submit" value="Search">
</form>

<%
    if (session.getAttribute("ManageableUser") != null) {
        User manageableUser = (User) (session.getAttribute("ManageableUser"));
%>

<span class="textStyle"><%=manageableUser.getUsername()%></span><br>
<span class="textStyle"><%=manageableUser.getEmail()%></span><br><br>

<form action="permitUser" method="post">
    <input class="loginButtonStyle" type="submit" value="Permit user">
</form>

<form action="forbidUser" method="post">
    <input class="deleteButtonStyle" type="submit" value="Forbid user">
</form>

<form action="deleteUser" method="post">
    <input class="deleteButtonStyle" type="submit" value="Delete user">
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
        session.removeAttribute("searchingUserNotFound");
    }
%>

</body>
</html>
