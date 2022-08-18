package controller;

import com.google.common.base.Optional;
import model.SearchingOption;
import service.BookContentDisplayService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(/*value = "/books",*/name = "BookSectionController")
public class BookSectionController extends HttpServlet {

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        Connection connection = ((Optional<Connection>) servletContext.getAttribute("dbConnection")).get();
        SearchingOption searchingOption = BookContentDisplayService.cacheSearchingOptions(request);
        session.setAttribute("searchingOption", searchingOption);
        if (session.getAttribute("CurrentUser") != null) {
            BookContentDisplayService.mainSearch(request, connection);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
        requestDispatcher.forward(request, response);
//        response.sendRedirect("BookSection.jsp");
    }

}
