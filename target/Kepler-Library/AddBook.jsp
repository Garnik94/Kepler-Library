<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="addNewBook" method="post">
    <label>Author<br>
        <input type="text" name="author">
    </label><br>
    <label>Title<br>
        <input type="text" name="title">
    </label><br>
    <label>Category<br>
        <input type="text" name="category">
    </label><br>
    <label>Language<br>
        <input type="text" name="language">
    </label><br>
    <label>Year<br>
        <input type="text" name="year">
    </label><br>
    <label>Document Type<br>
        <input type="text" name="documentType">
    </label><br>
    <label>Pages<br>
        <input type="text" name="pages">
    </label><br>
    <label>Download Url<br>
        <input type="text" name="downloadUrl">
    </label><br>
    <input type="submit" value="Add book">
</form>

</body>
</html>
