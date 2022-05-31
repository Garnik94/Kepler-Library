<%@ page import="model.User" %>
<%@ page import="service.ContentDisplayService" %>
<%@ page import="service.dao.CategoryDAO" %>
<%@ page import="service.dao.LanguageDAO" %>
<%@ page import="model.content.DocumentType" %>
<%@ page import="service.dao.DocumentTypeDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies.

    if (session.getAttribute("CurrentUser") == null /*|| session.getAttribute("password") == null*/) {
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
<body>

<%--<%--%>
<%--    User user = UserDAO.getUser(new User((String) session.getAttribute("username")));--%>
<%--%>--%>

<h1>
    <%= request.getParameter("page")%>
</h1>

<span class="userWelcomeMessage">
        <%=
        "Hi " + session.getAttribute("username")
        %>
    </span>
<form action="logout" method="post">
    <input type="submit" value="Logout">
</form>
<%
    User user = (User) session.getAttribute("CurrentUser");
    if (user.isHasEditPermission() == 1) { %>
<form action="AdminProfile.jsp" method="get">
    <input type="submit" value="Admin profile">
</form>
<% } %>

<div class="bigContainer">
    <div class="bigContainer">
        <form action="books" method="get">
            <span>Search</span><br>
            <label>
                <input class="inputAreaStyle" name="searchBook" type="text">
            </label><br>
            <label>By author<br>
                <input type="checkbox" name="author" value="authorChecked">
            </label>
            <label>By title<br>
                <input type="checkbox" name="title" value="titleChecked"><br>
            </label><br>
            <label>
                <select name="selectedCategory">
                    <option value="blankCategory" disabled selected>Select category</option>
                    <%
                        for (int i = 0; i < CategoryDAO.getCategories().size(); i++) {
                    %>
                    <option value="<%=CategoryDAO.getCategories().get(i).getCategoryName()%>"><%=CategoryDAO.getCategories().get(i).getCategoryName()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </label><br>
            <label>
                <select name="selectedLanguage">
                    <option value="blankLanguage" disabled selected>Select language</option>
                    <%
                        for (int i = 0; i < LanguageDAO.getLanguages().size(); i++) {
                    %>
                    <option value="<%=LanguageDAO.getLanguages().get(i).getLanguage()%>"><%=LanguageDAO.getLanguages().get(i).getLanguage()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </label><br>
            <label>
                <select name="selectedDocumentType">
                    <option value="blankDocumentType" disabled selected>Select Document type</option>
                    <%
                        for (int i = 0; i < DocumentTypeDAO.getDocumentTypes().size(); i++) {
                    %>
                    <option value="<%=DocumentTypeDAO.getDocumentTypes().get(i).getType()%>"><%=DocumentTypeDAO.getDocumentTypes().get(i).getType()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </label><br>
            <input type="submit" value="Search">
        </form>

        <%="book " + request.getParameter("searchBook")%>
        <%="author " + request.getParameter("author")%>
        <%="title " + request.getParameter("title")%>
        <%="category " + request.getParameter("selectedCategory")%>
        <%="language " + request.getParameter("selectedLanguage")%>
        <%="document type " + request.getParameter("selectedDocumentType")%>

    </div>

    <div class="contentContainer">
        <%

        %>
        <div class="contentWindow"></div>
    </div>

    <%
        if (session.getAttribute("searchingOption") != null) {
    %>

    <%
        int currentPage;
        if (request.getParameter("page") == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        int coefficient;
        if (currentPage == 1) {
            coefficient = 0;
        } else {
            coefficient = 3;
        }
        for (int i = (currentPage - 1) * coefficient; i <= (currentPage - 1) * coefficient + 3 - 1; i++) {
    %>
    <%
        if (i < ContentDisplayService.bookList.size()) {
    %>
    <p><%= ContentDisplayService.bookList.get(i)%>
    </p>
    <%
        if (user.isHasEditPermission() == 1) {
    %>
    <form>
        <input type="submit" value="Edit book">
    </form>
    <%
        }
    %>
    <%
        }
    %>
    <%
        }
    %>

    <%
        }
    %>

    <form>
        <%
            int condition = ContentDisplayService.bookList.size() % 3 == 1 ?
                    ContentDisplayService.bookList.size() / 3 + 1 : ContentDisplayService.bookList.size() / 3;

            for (int i = 1; i <= condition; i++) {
                String url = "booksPagination?page=" + i;
        %>
        <a href="<%=url%>"><%=i%>
        </a>
        <%
            }
        %>
    </form>

</div>

</body>
</html>
