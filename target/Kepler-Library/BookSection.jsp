<%@ page import="model.User" %>
<%@ page import="service.ContentDisplayService" %>
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
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body>

<%--<%--%>
<%--    User user = UserDAO.getUser(new User((String) session.getAttribute("username")));--%>
<%--%>--%>

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
<%
    User user = (User) session.getAttribute("CurrentUser");
    if (user.isHasEditPermission() == 1) { %>
<form action="AdminProfile.jsp" method="get">
    <input type="submit" value="Admin profile">
</form>
<% } %>

<div class="bigContainer">
    <div class="bigContainer">
        <form action="books" method="get">
            <label>
                <span>Search</span><br>
<%--                <%--%>
<%--                    String searchingArg = null;--%>
<%--                    if (request.getParameter("searchBook") != null) {--%>
<%--                        searchingArg = request.getParameter("searchBook");--%>
<%--                    }--%>
<%--                %>--%>
                <input class="inputAreaStyle" name="searchBook" type="text" value="<%=session.getAttribute("searchBook")%>">
                <%
                    if (session.getAttribute("searchBook") == null) {
                        session.setAttribute("searchBook", request.getParameter("searchBook"));
                    }
                %>
            </label>
            <input type="submit" value="Search">
        </form>

    </div>

    <label>Search<br>
        <input type="checkbox">
    </label>

    <div class="contentContainer">
        <%

        %>
        <div class="contentWindow"></div>
    </div>

    <%
        for (int i = 0; i < ContentDisplayService.bookList.size(); i++) {
    %>
    <p><%= ContentDisplayService.bookList.get(i)%>
    </p>
    <%
        if (user.isHasEditPermission() == 1) {
    %>
    <form>
        <input type="submit" value="Edit book">
    </form>
    <%
        }
    %>

    <%
        }
    %>

    <form>
        <%
            for (int i = 1; i < 10; i++) {
                String url = "booksPagination?page=" + i;
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
