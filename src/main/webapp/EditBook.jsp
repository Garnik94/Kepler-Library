<%@ page import="model.content.Book" %>
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
    <link rel="stylesheet" href="styles/styles.css">
</head>
<body class="standardPageBody">

<span class="userWelcomeMessage">
        <%=
        "Hi " + session.getAttribute("CurrentUser")
        %>
</span>

<form action="welcome" method="get">
    <input type="submit" value="Home">
</form>

<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>

    <%
        int currentEditableBookIndex = 0;
        if (request.getParameter("editableBook") != null) {
            currentEditableBookIndex = Integer.parseInt(request.getParameter("editableBook"));
        }
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

    <% if (session.getAttribute("ConfirmDeleteBook") != null) {%>
<%--        <jsp:include page="ConfirmDeleteBook.jsp"/>--%>
            <span class="errorMessageStyle">Are you really going to delete book</span>

            <form>
                <label>
                    <input type="submit" formaction="deleteBook?confirmDeleteBook=yes" formmethod="post" value="Yes">
                </label>
                <label>
                    <input type="submit" formaction="deleteBook?confirmDeleteBook=no" formmethod="post" value="No">
                </label>
            </form>
    <% } %>

    <% if (session.getAttribute("ConfirmEditBook") != null) {%>
<%--        <jsp:include page="ConfirmDeleteBook.jsp"/>--%>
            <span class="errorMessageStyle">Are you really going to edit book</span>

            <form>
                <label>
                    <input type="submit" formaction="editBook?confirmEditBook=yes" formmethod="post" value="Yes">
                </label>
                <label>
                    <input type="submit" formaction="editBook?confirmEditBook=no" formmethod="post" value="No">
                </label>
            </form>
    <% } %>


</body>
</html>
