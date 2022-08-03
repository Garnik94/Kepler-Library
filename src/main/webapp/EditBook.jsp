<%@ page import="model.content.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="shortcut icon" href="graphic/icon.png"
          type="image/x-icon">
    <title>Kepler library</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body class="standardPageBody">

<jsp:include page="UserProfile.jsp"/>

<br><br><br><br><br><br><br><br><br><br><br>

    <%
        int currentEditableBookIndex;
//        if (request.getParameter("editableBook") != null) {
            currentEditableBookIndex = Integer.parseInt(request.getParameter("editableBook"));
//        }
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

            <div style="display: inline-block; margin-left: 20px">
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
        <%
            String isDisable = "";
            if (session.getAttribute("ConfirmDeleteBook") != null ||
                    session.getAttribute("ConfirmEditBook") != null
            ){
                isDisable = "disabled";
            }
        %>

        <input <%=isDisable%> class="loginButtonStyle" type="submit" value="Edit book">
    </form>

    <form action="deleteBook?editableBook=<%=currentEditableBookIndex%>" method="post">
        <input <%=isDisable%> class="deleteButtonStyle" type="submit" value="Delete book">
    </form>

    <% if (session.getAttribute("ConfirmDeleteBook") != null) {%>
            <span class="errorMessageStyle">${ConfirmDeleteBook}</span>

            <br>

            <form>
                <label>
                    <input class="navigationButtons" type="submit" formaction="deleteBook?editableBook=<%=currentEditableBookIndex%>&&confirmDeleteBook=yes" formmethod="post" value="Yes">
                </label>
                <label>
                    <input class="navigationButtons" type="submit" formaction="deleteBook?editableBook=<%=currentEditableBookIndex%>&&confirmDeleteBook=no" formmethod="post" value="No">
                </label>
            </form>
    <%
        }
    %>

    <%
        if (session.getAttribute("ConfirmEditBook") != null)
        {
    %>
            <span class="errorMessageStyle">Are you really going to edit book</span>

            <form>
                <label>
                    <input type="submit" formaction="editBook?editableBook=<%=currentEditableBookIndex%>&&confirmEditBook=yes" formmethod="post" value="Yes">
                </label>
                <label>
                    <input type="submit" formaction="editBook?editableBook=<%=currentEditableBookIndex%>&&confirmEditBook=no" formmethod="post" value="No">
                </label>
            </form>
    <%
        }
    %>

</body>
</html>
