<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="mainPageBackground">

<div class="loginPage">

    <br><br><br><br><br><br>
    <div class="formDivStyle">

        <form>
            <label class="textStyle">Username<br>
                <input name="inputUsername" class="inputAreaStyle" type="text">
            </label><br>
            <span class="textStyle">Password</span><br>
            <label class="textStyle">
                <input name="inputPassword" class="inputAreaStyle" type="password">
            </label><br>
            <input formmethod="post" formaction="login" class="loginButtonStyle" type="submit" value="Login"><br>
            <input formmethod="post" formaction="Registration.jsp" class="registrationButtonStyle" type="submit"
                   value="Registration">
        </form>

        <%
            if (session.getAttribute("invalidLogin") != null) {
        %>
                <span class="errorMessageStyle">${sessionScope.invalidLogin}</span>
        <%
                session.removeAttribute("invalidLogin");
            }
        %>

    </div>
</div>

</body>
</html>
