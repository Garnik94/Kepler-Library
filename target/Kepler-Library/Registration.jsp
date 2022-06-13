<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("CurrentUser") != null) {
        request.getRequestDispatcher("BookSection.jsp").forward(request, response);
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
<body class="mainPageBackground">

<div class="loginPage">
    <form method="post" action="registration">
        <label class="textStyle">Enter username<br>
            <input name="username" class="inputAreaStyle" type="text">
        </label><br>
        <span class="textStyle">Enter email</span><br>
        <label class="textStyle">
            <input name="email" class="inputAreaStyle" type="text">
        </label><br>
        <span class="textStyle">Enter password</span><br>
        <label class="textStyle">
            <input name="password" class="inputAreaStyle" type="password">
        </label><br>
        <span class="textStyle">Confirm password</span><br>
        <label class="textStyle">
            <input name="confirmPassword" class="inputAreaStyle" type="password">
        </label><br>

        <input class="loginButtonStyle" type="submit" value="Register"><br>
    </form>

    <%
        if (request.getAttribute("MismatchedPasswords") != null &&
                request.getAttribute("MismatchedPasswords").equals("true")) {
    %>
    <span class="errorMessageStyle">Mismatched passwords</span>
    <%
        }
    %>

    <%
        if (request.getAttribute("RequiredInputError") != null &&
                request.getAttribute("RequiredInputError").equals("true")) {
    %>
    <span class="errorMessageStyle">All inputs are required</span>
    <%
        }
    %>

    <%
        if (request.getAttribute("UserIsAlreadyExists") != null &&
                request.getAttribute("UserIsAlreadyExists").equals("true")) {
    %>
    <span class="errorMessageStyle">User is already exists</span>
    <%
        }
    %>

</div>


</body>
</html>
