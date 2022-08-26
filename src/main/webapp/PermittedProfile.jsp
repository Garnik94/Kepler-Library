<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="standardPageBody">

<jsp:include page="UserProfile.jsp"/>

<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>

<form>
    <input class="loginButtonStyle" type="submit" formaction="AddBook.jsp" formmethod="get" value="Add new book"><br>
    <br><br>
    <input class="loginButtonStyle" type="submit" formaction="ManageUser.jsp" formmethod="get" value="Manage user">
</form>
</body>
</html>
