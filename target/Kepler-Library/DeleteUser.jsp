<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>

<form action="searchDeletableUser" method="get">
    <label>Search user<br>
        <input type="text" name="searchingUser">
    </label>
    <input type="submit" value="Search">
</form>

<%
    if (session.getAttribute("deletableUser") != null) {
%>
<p><%= (User)(session.getAttribute("deletableUser"))%></p>
<form action="deleteUser" method="post">
    <input type="submit" value="Delete">
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
