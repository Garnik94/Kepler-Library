<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" %>
<%
//    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//    response.setDateHeader("Expires", 0); // Proxies.

//    if (session.getAttribute("CurrentUser") != null) {
//        response.sendRedirect("BookSection.jsp");
//        return;
//    }
%>
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

    <div>
        <span class="mainLabel">WELCOME TO</span><br>
        <span class="mainLabel">KEPLER LIBRARY</span>
    </div>

    <div class="formDivStyle">

        <form>
            <label class="textStyle">Username<br>
                <input name="inputUsername" class="inputAreaStyle" type="text">
            </label><br>
            <span class="textStyle">Password</span><br>
            <label class="textStyle">
                <input name="inputPassword" class="inputAreaStyle" type="password">
            </label><br>
            <input formmethod="post" formaction="welcome" class="loginButtonStyle" type="submit" value="Login"><br>
            <input formmethod="post" formaction="Registration.jsp" class="registrationButtonStyle" type="submit"
                   value="Registration">
        </form>

        <%
            if (session.getAttribute("invalidLogin") != null /*&&
                    session.getAttribute("invalidLogin").equals("true")*/) {
        %>
        <span class="errorMessageStyle">${invalidLogin}</span>
        <%
                session.removeAttribute("invalidLogin");
            }
        %>

    </div>

</div>


</body>
</html>
