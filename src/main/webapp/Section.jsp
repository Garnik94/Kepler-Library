<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
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
    <form action="login" method="get">
        <input type="submit" value="Logout">
    </form>
<%
    if (request.getAttribute("adminMode") != null) {
%>
    <form action="adminProfile" method="get">
        <input type="submit" value="Admin profile">
    </form>
<%
    }
%>

<span class="textStyle">Choose section</span><br>
<form>
    <input class="bookSectionButtonStyle" type="submit" value="Books"><br>
    <input class="articleSectionButtonStyle" type="submit" value="Articles"><br>
</form>
</body>
</html>
