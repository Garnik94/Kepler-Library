package servlet;

import service.ContentDisplayService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ContentPaginationServlet")
public class ContentPaginationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = Integer.parseInt(request.getParameter("page"));
        HttpSession session = request.getSession();
//        ContentDisplayService.searchBooksByAuthor((String) session.getAttribute("searchingOption"), currentPage);
        ContentDisplayService.searchBooksByTitle((String) session.getAttribute("searchingOption"), currentPage);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
        requestDispatcher.forward(request, response);
    }
}
