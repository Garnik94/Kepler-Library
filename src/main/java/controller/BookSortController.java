package controller;

import com.google.common.base.Objects;
import service.BookContentDisplayService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(/*value = "/sortBooks", */name = "BookSortController")
public class BookSortController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortingOption = request.getParameter("sortingOption");
        HttpSession session = request.getSession();
        session.setAttribute("sortingOption", sortingOption);
        if (Objects.equal(sortingOption, "Recently added")){
            BookContentDisplayService.sortBooksByRecentlyAdded(1);
        } else if (Objects.equal(sortingOption,"Last added")) {
            BookContentDisplayService.sortBooksByRecentlyAdded(-1);
        }
        if (Objects.equal(sortingOption, "Title (A-Z)")){
            BookContentDisplayService.sortBooksByTitle(1);
        } else if (Objects.equal(sortingOption, "Title (Z-A)")){
            BookContentDisplayService.sortBooksByTitle(-1);
        }
        if (Objects.equal(sortingOption, "Page up -> down")){
            BookContentDisplayService.sortBooksByPage(1);
        } else if (Objects.equal(sortingOption, "Page down -> up")){
            BookContentDisplayService.sortBooksByPage(-1);
        }
        if (Objects.equal(sortingOption, "Year up -> down")){
            BookContentDisplayService.sortBooksByYear(1);
        } else if (Objects.equal(sortingOption,"Year down -> up")){
            BookContentDisplayService.sortBooksByYear(-1);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
        requestDispatcher.forward(request, response);
//        response.sendRedirect("BookSection.jsp");
    }
}
