<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="mainPageBackground">

<br><br><br><br><br><br><br><br><br>

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
        if (session.getAttribute("mismatchedPasswords") != null) {
    %>
            <span class="errorMessageStyle">${sessionScope.mismatchedPasswords}</span>
    <%
            session.removeAttribute("mismatchedPasswords");
        }
    %>

    <%
        if (session.getAttribute("requiredInputError") != null) {
    %>
            <span class="errorMessageStyle">${sessionScope.requiredInputError}</span>
    <%
            session.removeAttribute("requiredInputError");
        }
    %>

    <%
        if (session.getAttribute("userIsAlreadyExists") != null) {
    %>
            <span class="errorMessageStyle">${sessionScope.userIsAlreadyExists}</span>
    <%
            session.removeAttribute("userIsAlreadyExists");
        }
    %>

</div>

</body>
</html>
