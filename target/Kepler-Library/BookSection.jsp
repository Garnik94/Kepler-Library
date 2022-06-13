<%@ page import="model.User" %>
<%@ page import="model.content.Book" %>
<%@ page import="service.BookContentDisplayService" %>
<%@ page import="service.dao.CategoryDAO" %>
<%@ page import="service.dao.DocumentTypeDAO" %>
<%@ page import="service.dao.LanguageDAO" %>
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
    User user = (User) session.getAttribute("CurrentUser");
    if (user.isHasEditPermission() == 1) { %>
<form action="AdminProfile.jsp" method="get">
    <input type="submit" value="Admin profile">
</form>
<% } %>

<div class="bigContainer">
    <div class="bigContainer">
        <form action="books" method="get">
            <span class="textStyle">Search</span><br>
            <label>
                <input class="inputAreaStyle" name="searchBook" type="text">
            </label><br>
            <label><br>
                <select name="searchBy">
                    <option>Author</option>
                    <option>Title</option>
                </select>
            </label>
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
            </label>
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
            </label>
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
            </label>
            <input type="submit" value="Search">
        </form>

        <%
            session.getAttribute("searchingOption");
        %>

    </div>

    <%
        if (session.getAttribute("searchingOption") != null && session.getAttribute("inputValidationError") == null) {
    %>

    <form action="sortBooks" method="get">

        <select name="sortingOption">
            <option disabled>Check sorting option</option>
            <option value="recentlyAdded">Recently added</option>
            <option value="bookTitleUp">Title (A-Z)</option>
            <option value="bookTitleDown">Title (Z-A)</option>
            <option value="bookPageUp">Page up -> down</option>
            <option value="bookPageDown">Page down -> up</option>
            <option value="bookYearUp">year up -> down</option>
            <option value="bookYearDown">year down -> up</option>
        </select>

        <input type="submit" value="sort">

    </form>

    <div class="contentContainer">
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
                coefficient = 10;
            }
            for (int i = (currentPage - 1) * coefficient, j = 0; i <= (currentPage - 1) * coefficient + 10 - 1; i++, j++) {
        %>
        <%
            if (i < BookContentDisplayService.bookList.size()) {
        %>
        <div class="contentWindow">
            <div style="margin-top: 20px">
                <%Book book = BookContentDisplayService.bookList.get(i);%>
                <%=book.getAuthor()%><br>
                <%=book.getTitle()%><br>
                <%="Category: " + book.getCategory()%><br>
                <%="Language: " + book.getLanguage()%><br>
                <%="Year: " + book.getYear()%><br>
                <%="Pages: " + book.getPages()%><br>
                <%="Type: " + book.getDocumentType()%><br>
            </div>
            <a href="https://drive.google.com/drive/folders/149ziSQc2CgwNQhF4J9caU8JfAwwXNbIC?usp=sharing"
               target="_blank">Download
            </a>

            <%
                if (user.isHasEditPermission() == 1) {
                    session.setAttribute(String.valueOf(j), book);
            %>

            <a href="EditBook.jsp?editableBook=<%=i%>">Edit book</a>

            <%
                }
            %>
        </div>
        <%
            }
        %>
        <%
            }
        %>

    </div>

    <form>
        <%
            int condition = BookContentDisplayService.bookList.size() % 10 >= 1 ?
                    BookContentDisplayService.bookList.size() / 10 + 1 : BookContentDisplayService.bookList.size() / 10;

            for (int i = 1; i <= condition; i++) {
                String url = "BookSection.jsp?page=" + i;
        %>
        <%
            if (i == currentPage) {
        %>
        <a class="selectedPage" href="<%=url%>"><%=i%>
        </a>
        <%
        } else {
        %>
        <a class="paginationButton" href="<%=url%>"><%=i%>
        </a>
        <%
            }
        %>
        <%
            }
        %>
    </form>

    <%
    } else if (session.getAttribute("inputValidationError") != null) {
    %>
    <h1 class="errorMessageStyle">input value min length must be more than 3</h1>
    <%
        }
    %>

</div>

</body>
</html>
