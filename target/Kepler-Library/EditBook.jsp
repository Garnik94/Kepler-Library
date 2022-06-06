<%@ page import="model.content.Book" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 04.06.2022
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>

    <%
        int currentEditableBookIndex = Integer.parseInt(request.getParameter("editableBook"));
        Book editableBook = (Book) session.getAttribute(String.valueOf(currentEditableBookIndex));
        session.setAttribute("checkedBook", editableBook);
    %>

    <form action="editBook" method="post">
        <div>
            <div style="display: inline-block; margin-right: 20px">
                <label class="textStyle">Author<br>
                    <input class="inputAreaStyle" type="text" name="editAuthor" value="<%=editableBook.getAuthor()%>">
                </label><br>
                <label class="textStyle">Title<br>
                    <input class="inputAreaStyle" type="text" name="editTitle" value="<%=editableBook.getTitle()%>">
                </label><br>
                <label class="textStyle">Category<br>
                    <input class="inputAreaStyle" type="text" name="editCategory" value="<%=editableBook.getCategory()%>">
                </label><br>
                <label class="textStyle">Language<br>
                    <input class="inputAreaStyle" type="text" name="editLanguage" value="<%=editableBook.getLanguage()%>">
                </label><br>
            </div>

            <div style="display: inline-block; margin-left: 20px"<%--style="float: right; margin-right: 450px"--%>>
                <label class="textStyle">Year<br>
                    <input class="inputAreaStyle" type="text" name="editYear" value="<%=editableBook.getYear()%>">
                </label><br>
                <label class="textStyle">Document Type<br>
                    <input class="inputAreaStyle" type="text" name="editDocumentType" value="<%=editableBook.getDocumentType()%>">
                </label><br>
                <label class="textStyle">Pages<br>
                    <input class="inputAreaStyle" type="text" name="editPages" value="<%=editableBook.getPages()%>">
                </label><br>
                <label class="textStyle">Download Url<br>
                    <input class="inputAreaStyle" type="text" name="editDownloadUrl" value="<%=editableBook.getDownloadUrl()%>">
                </label><br>
            </div>
        </div>
        <input  type="submit" value="Edit book">
    </form>

    <form action="deleteBook" method="post">
        <input type="submit" value="Delete book">
    </form>

    <h1>
    <%=
       editableBook
    %>
    </h1>

</body>
</html>
