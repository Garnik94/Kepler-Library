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
<body>

<span class="userWelcomeMessage">
        <%=
        "Hi " + session.getAttribute("CurrentUser")
        %>
</span>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>

<form>
    <input type="submit" formaction="AddBook.jsp" formmethod="get" value="Add new book">
    <input type="submit" formaction="AddArticle.jsp" formmethod="get" value="Add new article">
    <input type="submit" formaction="ManageUser.jsp" formmethod="get" value="Manage user">
</form>
</body>
</html>
