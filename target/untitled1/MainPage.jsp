<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="service.MainDynamicPageService" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body>
<div>
    <span class="mainLabel">WELCOME TO</span><br>
    <span class="mainLabel">KEPLER LIBRARY</span>
</div>

<div class="formDivStyle">
    <form>
        <%
            if (MainDynamicPageService.isLoginPage()) {
        %>
        <label class="textStyle">Username<br>
            <input name="inputUsername" class="inputAreaStyle" type="text">
        </label><br>
        <span class="textStyle">Password</span><br>
        <label class="textStyle">
            <input name="inputPassword" class="inputAreaStyle" type="password">
        </label><br>
        <input formmethod="post" formaction="chooseSection" class="loginButtonStyle" type="submit" value="Login"><br>
        <input formmethod="post" formaction="registration" class="registrationButtonStyle" type="submit" value="Registration" >
        <%
            }
        %>

        <%
            if (MainDynamicPageService.isRegistrationPage()) {
        %>
        <form>
            <label class="textStyle">Enter username<br>
                <input class="inputAreaStyle" type="text">
            </label><br>
            <span class="textStyle">Enter email</span><br>
            <label class="textStyle">
                <input class="inputAreaStyle" type="password">
            </label><br>
            <span class="textStyle">Enter password</span><br>
            <label class="textStyle">
                <input class="inputAreaStyle" type="password">
            </label><br>
            <span class="textStyle">Confirm password</span><br>
            <label class="textStyle">
                <input class="inputAreaStyle" type="password">
            </label><br>
            <input formmethod="get" formaction="login" class="loginButtonStyle" type="submit" value="Register" ><br>
        </form>
        <%
            }
        %>

    </form>
</div>

</body>
</html>
