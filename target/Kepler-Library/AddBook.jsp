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

    <input  type="submit" value="Add book">

</form>


</body>
</html>
