package servlet;

import model.SearchingOption;
import model.content.Category;
import model.content.DocumentType;
import model.content.Journal;
import model.content.Language;
import service.ArticleContentDisplayService;
import service.BookContentDisplayService;
import service.dao.CategoryDAO;
import service.dao.DocumentTypeDAO;
import service.dao.JournalDAO;
import service.dao.LanguageDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ArticleSectionServlet")
public class ArticleSectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("ArticleSection.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        if (request.getParameter("searchBook").length() < 3) {
//            session.setAttribute("inputValidationError", true);
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
//            requestDispatcher.forward(request, response);
//            return;
//        }
        session.removeAttribute("inputValidationError");
        Category category = null;
        if (request.getParameter("selectedCategory") != null) {
            category = new Category(request.getParameter("selectedCategory"));
            category.setId(CategoryDAO.getCategoryIdByName(category));
        }
        DocumentType documentType = null;
        if (request.getParameter("selectedDocumentType") != null) {
            documentType = new DocumentType(request.getParameter("selectedDocumentType"));
            documentType.setId(DocumentTypeDAO.getDocumentTypeIdByName(documentType));
        }
        Language language = null;
        if (request.getParameter("selectedLanguage") != null) {
            language = new Language(request.getParameter("selectedLanguage"));
            language.setId(LanguageDAO.getLanguageIdByName(language));
        }
        Journal journal = null;
        if (request.getParameter("selectedJournal") != null) {
            journal = new Journal(request.getParameter("selectedJournal"));
            journal.setId(JournalDAO.getJournalIdByName(journal));
        }
        SearchingOption searchingOption = new SearchingOption(request.getParameter("searchBook"),
                request.getParameter("searchBy"),
                category,
                documentType,
                language);
        searchingOption.setJournal(journal);
        session.setAttribute("searchingOption", searchingOption);
        ArticleContentDisplayService.mainSearch(request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
        requestDispatcher.forward(request, response);
    }

}
