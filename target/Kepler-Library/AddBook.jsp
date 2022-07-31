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

<jsp:include page="UserProfile.jsp"/>

<%--<div style="float: right; text-align: center; width: 300px">--%>
<%--    <span class="textStyle">--%>
<%--        ${CurrentUser}--%>
<%--    </span> <br><br>--%>

<%--    <form action="welcome" method="get">--%>
<%--        <input class="navigationButtons" type="submit" value="Home">--%>
<%--    </form>--%>

<%--    <%--%>
<%--        User user = (User) session.getAttribute("CurrentUser");--%>
<%--        if (user.isHasEditPermission() == 1) { %>--%>
<%--    <form action="AdminProfile.jsp" method="get">--%>
<%--        <input class="navigationButtons" type="submit" value="Manage">--%>
<%--    </form>--%>
<%--    <% } %>--%>

<%--    <form action="logout" method="post">--%>
<%--        <input class="navigationButtons" type="submit" value="Logout">--%>
<%--    </form>--%>
<%--</div>--%>

<br><br><br><br><br><br><br><br><br><br><br>

<form action="addNewBook" method="post">

    <div>
        <div style="display: inline-block; margin-right: 20px">
            <label class="textStyle">Author<br>
                <input class="inputAreaStyle" type="text" name="author">
            </label><br>
            <label class="textStyle">Title<br>
                <input class="inputAreaStyle" type="text" name="title">
            </label><br>
            <label class="textStyle">Category<br>
                <input class="inputAreaStyle" type="text" name="category">
            </label><br>
            <label class="textStyle">Language<br>
                <input class="inputAreaStyle" type="text" name="language">
            </label><br>
        </div>

        <div style="display: inline-block; margin-left: 20px"<%--style="float: right; margin-right: 450px"--%>>
            <label class="textStyle">Year<br>
                <input class="inputAreaStyle" type="text" name="year">
            </label><br>
            <label class="textStyle">Document Type<br>
                <input class="inputAreaStyle" type="text" name="documentType">
            </label><br>
            <label class="textStyle">Pages<br>
                <input class="inputAreaStyle" type="text" name="pages">
            </label><br>
            <label class="textStyle">Download Url<br>
                <input class="inputAreaStyle" type="text" name="downloadUrl">
            </label><br>
        </div>
    </div>

    <input class="loginButtonStyle" type="submit" value="Add book">

</form>


</body>
</html>
