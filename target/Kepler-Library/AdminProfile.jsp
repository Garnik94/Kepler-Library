<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>

<form>
    <input type="submit" formaction="addNewBook" formmethod="get" value="Add new book">
    <input type="submit" value="Delete book">
    <input type="submit" formaction="addNewArticle" formmethod="get" value="Add new article">
    <input type="submit" value="Delete article">
    <input type="submit" formaction="deleteUser" formmethod="get" value="Delete user">
</form>
</body>
</html>
