<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("username") == null || session.getAttribute("password") == null) {
        request.getRequestDispatcher("MainPage.jsp").forward(request, response);
    }
%>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.
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
<form action="login" method="post">
    <input type="submit" value="Logout">
</form>
<span class="textStyle">Choose section</span><br>
<form>
    <input class="bookSectionButtonStyle" type="submit" value="Books"><br>
    <input class="articleSectionButtonStyle" type="submit" value="Articles"><br>
</form>
</body>
</html>
