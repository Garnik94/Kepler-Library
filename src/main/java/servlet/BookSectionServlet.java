package servlet;

import model.SearchingOption;
import model.content.Category;
import model.content.DocumentType;
import model.content.Language;
import service.ContentDisplayService;
import service.dao.CategoryDAO;
import service.dao.DocumentTypeDAO;
import service.dao.LanguageDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "BookSectionServlet")
public class BookSectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String searchingArg = request.getParameter("searchBook");
//        int currentPage;
//        if (request.getParameter("page") == null) {
//            currentPage = 1;
//        } else {
//            currentPage = Integer.parseInt(request.getParameter("page"));
//        }
//        ContentDisplayService.searchBooksByAuthor(searchingArg, currentPage - 1);
////        HttpSession session = request.getSession();
////        session.setAttribute("found");
//        response.sendRedirect("BookSection.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (request.getParameter("searchBook").length() < 3) {
            session.setAttribute("inputValidationError", true);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
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
        SearchingOption searchingOption = new SearchingOption(request.getParameter("searchBook"),
                request.getParameter("searchBy"),
                category,
                documentType,
                language);
        session.setAttribute("searchingOption", searchingOption);
        ContentDisplayService.mainSearch(request);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
        requestDispatcher.forward(request, response);
    }
}
