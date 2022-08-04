package controller;

import model.SearchingOption;
import service.BookContentDisplayService;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection) servletContext.getAttribute("dbConnection");
        SearchingOption searchingOption = BookContentDisplayService.cacheSearchingOptions(request);
        session.setAttribute("searchingOption", searchingOption);
        if (session.getAttribute("CurrentUser") != null) {
            BookContentDisplayService.mainSearch(request, connection);
        }
        response.sendRedirect("BookSection.jsp");
    }

}
