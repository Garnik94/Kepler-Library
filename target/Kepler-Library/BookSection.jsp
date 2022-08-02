<%@ page import="model.SearchingOption" %>
<%@ page import="model.User" %>
<%@ page import="model.content.Book" %>
<%@ page import="service.BookContentDisplayService" %>
<%@ page import="service.dao.CategoryDAO" %>
<%@ page import="service.dao.DocumentTypeDAO" %>
<%@ page import="service.dao.LanguageDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("CurrentUser");
%>
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

<div class="bigContainer">
    <div class="bigContainer">
        <form action="books" method="get">
            <%
                SearchingOption searchingOption = (SearchingOption) session.getAttribute("searchingOption");
            %>

            <span class="textStyle">Search by</span><br><br>
            <label>
                <select class="multiSelectTextStyle" name="searchBy">

                    <option>Author</option>
                    <option>Title</option>
                </select>
            </label>
            <label>
                <select class="multiSelectTextStyle" name="selectedCategory">
                    <%
                        String selectedCategory = "";
                        if (searchingOption == null ||
                                (searchingOption.getCategory() != null &&
                                        searchingOption.getCategory().getCategoryName().equals("All Categories"))) {
                            selectedCategory = "selected";
                        }
                    %>
                    <option value="All Categories" <%=selectedCategory%>>All Categories</option>
                    <%
                        selectedCategory = "";
                        for (int i = 0; i < CategoryDAO.getCategories().size(); i++) {
                            if (searchingOption != null && searchingOption.getCategory() != null &&
                                    searchingOption.getCategory().equals(CategoryDAO.getCategories().get(i))) {
                                selectedCategory = "selected";
                            }
                    %>
                    <option value="<%=CategoryDAO.getCategories().get(i).getCategoryName()%>" <%=selectedCategory%>><%=CategoryDAO.getCategories().get(i).getCategoryName()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </label>
            <label>
                <select class="multiSelectTextStyle" name="selectedLanguage">
                    <%
                        String selectedLanguage = "";
                        if (searchingOption == null ||
                                (searchingOption.getLanguage() != null &&
                                        searchingOption.getLanguage().getLanguage().equals("All Languages"))) {
                            selectedLanguage = "selected";
                        }
                    %>
                    <option value="All Languages" <%=selectedLanguage%>>All language</option>
                    <%
                        selectedLanguage = "";
                        for (int i = 0; i < LanguageDAO.getLanguages().size(); i++) {
                            if (searchingOption != null && searchingOption.getLanguage() != null &&
                                    searchingOption.getLanguage().equals(LanguageDAO.getLanguages().get(i))) {
                                selectedLanguage = "selected";
                            }
                    %>
                    <option value="<%=LanguageDAO.getLanguages().get(i).getLanguage()%>" <%=selectedLanguage%>><%=LanguageDAO.getLanguages().get(i).getLanguage()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </label>
            <label>
                <select class="multiSelectTextStyle" name="selectedDocumentType">
                    <%
                        String selectedDocumentType = "";
                        if (searchingOption == null ||
                                (searchingOption.getDocumentType() != null &&
                                        searchingOption.getDocumentType().getType().equals("All Document Types"))) {
                            selectedDocumentType = "selected";
                        }
                    %>
                    <option value="All Document Types" selected <%=selectedDocumentType%>>All document type</option>
                    <%
                        selectedDocumentType = "";
                        for (int i = 0; i < DocumentTypeDAO.getDocumentTypes().size(); i++) {
                            if (searchingOption != null && searchingOption.getDocumentType() != null &&
                                    searchingOption.getDocumentType().equals(DocumentTypeDAO.getDocumentTypes().get(i))) {
                                selectedDocumentType = "selected";
                            }
                    %>
                    <option value="<%=DocumentTypeDAO.getDocumentTypes().get(i).getType()%>" <%=selectedDocumentType%>><%=DocumentTypeDAO.getDocumentTypes().get(i).getType()%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </label><br>
            <label>
                <input class="searchInputAreaStyle" name="searchBook" type="text">
            </label>
            <input class="searchButton" type="submit" value="Search">
        </form>

    </div>

    <%
        if (session.getAttribute("searchingOption") != null && session.getAttribute("inputValidationError") == null) {
    %>

    <form action="sortBooks" method="get">

        <label>
            <select class="multiSelectTextStyle" name="sortingOption">
                <option disabled>Check sorting option</option>
                <option value="recentlyAdded">Recently added</option>
                <option value="bookTitleUp">Title (A-Z)</option>
                <option value="bookTitleDown">Title (Z-A)</option>
                <option value="bookPageUp">Page up -> down</option>
                <option value="bookPageDown">Page down -> up</option>
                <option value="bookYearUp">year up -> down</option>
                <option value="bookYearDown">year down -> up</option>
            </select>
        </label>

        <input class="searchButton" type="submit" value="Sort">

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
            <div class="contentTextStyle" style="margin-top: 20px">
                <%Book book = BookContentDisplayService.bookList.get(i);%>
                <span style="font-weight: bold"><%=book.getAuthor()%></span><br>
                <span style="font-weight: bold"><%=book.getTitle()%></span><br>
                <%="Category: " + book.getCategory()%><br>
                <%="Language: " + book.getLanguage()%><br>
                <%="Year: " + book.getYear()%><br>
                <%="Pages: " + book.getPages()%><br>
                <%="Type: " + book.getDocumentType()%><br>
            </div>
            <br>
            <a class="linksLikeButton" href="<%=book.getDownloadUrl()%>"
               target="_blank">Download</a><br><br>

            <%
                if (user.isHasEditPermission() == 1) {
                    session.setAttribute(String.valueOf(i), book);
            %>

            <a class="linksLikeButton" href="EditBook.jsp?editableBook=<%=i%>">Edit book</a>

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
