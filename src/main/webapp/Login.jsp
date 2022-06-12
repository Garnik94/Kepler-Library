<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.

    if (session.getAttribute("CurrentUser") != null){
        response.sendRedirect("Welcome.jsp");
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
<div>
    <span class="mainLabel">WELCOME TO</span><br>
    <span class="mainLabel">KEPLER LIBRARY</span>
</div>

<div class="formDivStyle">

    <%
        if (request.getAttribute("invalidLogin") != null &&
                request.getAttribute("invalidLogin").equals("true")) {
    %>
        <span class="errorMessageStyle">Username or password is wrong</span>
    <%
        }
    %>

    <form>
        <label class="textStyle">Username<br>
            <input name="inputUsername" class="inputAreaStyle" type="text">
        </label><br>
        <span class="textStyle">Password</span><br>
        <label class="textStyle">
            <input name="inputPassword" class="inputAreaStyle" type="password">
        </label><br>
        <input formmethod="post" formaction="welcome" class="loginButtonStyle" type="submit" value="Login"><br>
        <input formmethod="get" formaction="Registration.jsp" class="registrationButtonStyle" type="submit"
               value="Registration">
    </form>
</div>

</body>
</html>
