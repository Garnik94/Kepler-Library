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

@WebServlet(name = "BookSectionServlet")
public class BookSectionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchingArg = request.getParameter("searchBook");
        int currentPage;
        if (request.getParameter("page") == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        ContentDisplayService.searchBooksByAuthor(searchingArg, currentPage - 1);
//        HttpSession session = request.getSession();
//        session.setAttribute("found");
        response.sendRedirect("BookSection.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchingArg = request.getParameter("searchBook");
        HttpSession session = request.getSession();
        if (session.getAttribute("searchingOption") == null) {
            session.setAttribute("searchingOption", searchingArg);
        }
        int currentPage;
        if (request.getParameter("page") == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(request.getParameter("page"));
        }
        ContentDisplayService.searchBooksByAuthor((String) session.getAttribute("searchingOption"),
                currentPage - 1);
//        HttpSession session = request.getSession();
//        session.setAttribute("found");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
        requestDispatcher.forward(request, response);
//        String searchingArg = request.getParameter("searchBook");
//        int currentPage;
//        if (request.getParameter("page") == null){
//            currentPage = 1;
//        } else {
//            currentPage = Integer.parseInt(request.getParameter("page"));
//        }
//        ContentDisplayService.searchBooksByAuthor(searchingArg, currentPage - 1);
////        HttpSession session = request.getSession();
////        session.setAttribute("found");
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
//        requestDispatcher.forward(request, response);
//        response.sendRedirect("BookSection.jsp");
    }
}
