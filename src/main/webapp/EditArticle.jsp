<%@ page import="model.content.Article" %>
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
    int currentEditableArticleIndex = 0;
    if (request.getParameter("editableArticle") != null) {
        currentEditableArticleIndex = Integer.parseInt(request.getParameter("editableArticle"));
    }
    Article editableArticle = (Article) session.getAttribute(String.valueOf(currentEditableArticleIndex));
    session.setAttribute("checkedArticle", editableArticle);
%>

<form action="editArticle" method="post">
    <div>
        <div style="display: inline-block; margin-right: 20px">
            <label class="textStyle">Author<br>
                <input class="inputAreaStyle" type="text" name="editAuthor" value="<%=editableArticle.getAuthor()%>">
            </label><br>
            <label class="textStyle">Title<br>
                <input class="inputAreaStyle" type="text" name="editTitle" value="<%=editableArticle.getTitle()%>">
            </label><br>
            <label class="textStyle">Category<br>
                <input class="inputAreaStyle" type="text" name="editCategory"
                       value="<%=editableArticle.getCategory()%>">
            </label><br>
            <label class="textStyle">Language<br>
                <input class="inputAreaStyle" type="text" name="editLanguage"
                       value="<%=editableArticle.getLanguage()%>">
            </label><br>
        </div>

        <div style="display: inline-block; margin-left: 20px"<%--style="float: right; margin-right: 450px"--%>>
            <label class="textStyle">Year<br>
                <input class="inputAreaStyle" type="text" name="editYear" value="<%=editableArticle.getYear()%>">
            </label><br>
            <label class="textStyle">Document Type<br>
                <input class="inputAreaStyle" type="text" name="editDocumentType"
                       value="<%=editableArticle.getDocumentType()%>">
            </label><br>
            <label class="textStyle">Journal<br>
                <input class="inputAreaStyle" type="text" name="editJournal" value="<%=editableArticle.getJournal()%>">
            </label><br>
            <label class="textStyle">Download Url<br>
                <input class="inputAreaStyle" type="text" name="editDownloadUrl"
                       value="<%=editableArticle.getDownloadUrl()%>">
            </label><br>
        </div>
    </div>
    <input type="submit" value="Edit article">
</form>

<form action="deleteArticle" method="post">
    <input type="submit" value="Delete article">
</form>

<% if (session.getAttribute("ConfirmDeleteArticle") != null) {%>
<span class="errorMessageStyle">Are you really going to delete article</span>

<form>
    <label>
        <input type="submit" formaction="deleteArticle?confirmDeleteArticle=yes" formmethod="post" value="Yes">
    </label>
    <label>
        <input type="submit" formaction="deleteArticle?confirmDeleteArticle=no" formmethod="post" value="No">
    </label>
</form>
<% } %>

<% if (session.getAttribute("ConfirmDeleteArticle") != null) {%>
<span class="errorMessageStyle">Are you really going to edit article</span>

<form>
    <label>
        <input type="submit" formaction="editArticle?confirmEditArticle=yes" formmethod="post" value="Yes">
    </label>
    <label>
        <input type="submit" formaction="editArticle?confirmEditArticle=no" formmethod="post" value="No">
    </label>
</form>
<% } %>


</body>
</html>
