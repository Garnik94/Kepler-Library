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

    <span class="errorMessageStyle">Are you sure you are going to delete book</span>

    <form>
        <label>
            <input type="submit" formaction="deleteBook" formmethod="post" value="Yes">
        </label>
        <label>
            <input type="submit" formaction="EditBook.jsp" value="No" onclick="<%session.removeAttribute("needToConfirm");%>">
        </label>
    </form>

</body>
</html>
