<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session.getAttribute("CurrentUser") != null) {
        request.getRequestDispatcher("Welcome.jsp").forward(request, response);
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

<form>
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
    <input formmethod="post" formaction="registration" class="loginButtonStyle" type="submit" value="Register"><br>
</form>

<%
    if (request.getAttribute("mismatchedPasswords") != null &&
            request.getAttribute("mismatchedPasswords").equals("true")) {
%>
        <span class="errorMessageStyle">Mismatched passwords</span>
<%
    }
%>

</body>
</html>
