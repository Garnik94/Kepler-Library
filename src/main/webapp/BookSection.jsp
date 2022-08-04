<%@ page import="model.SearchingOption" %>
<%@ page import="model.User" %>
<%@ page import="model.content.Book" %>
<%@ page import="service.BookContentDisplayService" %>
<%@ page import="service.dao.CategoryDAO" %>
<%@ page import="service.dao.DocumentTypeDAO" %>
<%@ page import="service.dao.LanguageDAO" %>
<%@ page import="com.google.common.base.Objects" %>
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
                                        Objects.equal(searchingOption.getCategory().getCategoryName(), "All Categories"))) {
                            selectedCategory = "selected";
                        }
                    %>
                    <option value="All Categories" <%=selectedCategory%>>All Categories</option>
                    <%
                        selectedCategory = "";
                        for (int i = 0; i < CategoryDAO.getCategories().size(); i++) {
                            if (searchingOption != null && searchingOption.getCategory() != null &&
                                    Objects.equal(searchingOption.getCategory(), CategoryDAO.getCategories().get(i))) {
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
                                        Objects.equal(searchingOption.getLanguage().getLanguage(), "All Languages"))) {
                            selectedLanguage = "selected";
                        }
                    %>
                    <option value="All Languages" <%=selectedLanguage%>>All language</option>
                    <%
                        selectedLanguage = "";
                        for (int i = 0; i < LanguageDAO.getLanguages().size(); i++) {
                            if (searchingOption != null && searchingOption.getLanguage() != null &&
                                    Objects.equal(searchingOption.getLanguage(), LanguageDAO.getLanguages().get(i))) {
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
                                        Objects.equal(searchingOption.getDocumentType().getType(), "All Document Types"))) {
                            selectedDocumentType = "selected";
                        }
                    %>
                    <option value="All Document Types" selected <%=selectedDocumentType%>>All document type</option>
                    <%
                        selectedDocumentType = "";
                        for (int i = 0; i < DocumentTypeDAO.getDocumentTypes().size(); i++) {
                            if (searchingOption != null && searchingOption.getDocumentType() != null &&
                                    Objects.equal(searchingOption.getDocumentType(), DocumentTypeDAO.getDocumentTypes().get(i))) {
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
                <%--                <option disabled>Check sorting option</option>--%>
                <%
                    for (int i = 0; i < BookContentDisplayService.sortingOptions.size(); i++) {
                        String selectOptionSelected = "";
                        String selectOptionName = BookContentDisplayService.sortingOptions.get(i);
                        if (Objects.equal(selectOptionName, session.getAttribute("sortingOption"))) {
                            selectOptionSelected = "selected";
                        }
                %>

                <option value="<%=selectOptionName%>" <%=selectOptionSelected%>><%=selectOptionName%>
                </option>

                <%--                <option value="Recently added">Recently added</option>--%>
                <%--                <option value="Title (A-Z)">Title (A-Z)</option>--%>
                <%--                <option value="Title (Z-A)">Title (Z-A)</option>--%>
                <%--                <option value="Page up -> down">Page up -> down</option>--%>
                <%--                <option value="Page down -> up">Page down -> up</option>--%>
                <%--                <option value="Year up -> down">Year up -> down</option>--%>
                <%--                <option value="Year down -> up">Year down -> up</option>--%>

                <%
                    }
                %>
            </select>
        </label>

        <input class="searchButton" type="submit" value="Sort">

    </form>

    <div class="contentContainer">
        <%
            int bookListSize = BookContentDisplayService.getBookList().size();
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
            if (i < bookListSize) {
        %>
        <div class="contentWindow">
            <div class="contentTextStyle" style="margin-top: 20px">
                <%Book book = BookContentDisplayService.getBookList().get(i);%>
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
            int condition = bookListSize % 10 >= 1 ?
                    bookListSize / 10 + 1 : bookListSize / 10;

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
