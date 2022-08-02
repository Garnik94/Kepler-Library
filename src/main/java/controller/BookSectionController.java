package controller;

import model.SearchingOption;
import model.content.Category;
import model.content.DocumentType;
import model.content.Language;
import service.BookContentDisplayService;
import service.dao.CategoryDAO;
import service.dao.DocumentTypeDAO;
import service.dao.LanguageDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "BookSectionServlet")
public class BookSectionController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection) servletContext.getAttribute("dbConnection");
//        if (request.getParameter("searchBook").length() < 3) {
//            session.setAttribute("inputValidationError", true);
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
//            requestDispatcher.forward(request, response);
//            return;
//        }
//        session.removeAttribute("inputValidationError");
        SearchingOption searchingOption = BookContentDisplayService.cacheSearchingOptions(request);
        session.setAttribute("searchingOption", searchingOption);
        if (session.getAttribute("CurrentUser") != null) {
            BookContentDisplayService.mainSearch(request, connection);
        }
        response.sendRedirect("BookSection.jsp");
    }

}
