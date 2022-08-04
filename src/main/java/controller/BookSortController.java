package controller;

import service.BookContentDisplayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SortContentServlet")
public class BookSortController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortingOption = request.getParameter("sortingOption");
        HttpSession session = request.getSession();
        session.setAttribute("sortingOption", sortingOption);
        if (sortingOption.equals("Recently added")){
            BookContentDisplayService.sortBooksByRecentlyAdded();
        }
        if (sortingOption.equals("Title (A-Z)")){
            BookContentDisplayService.sortBooksByTitle(1);
        } else if (sortingOption.equals("Title (Z-A)")){
            BookContentDisplayService.sortBooksByTitle(-1);
        }
        if (sortingOption.equals("Page up -> down")){
            BookContentDisplayService.sortBooksByPage(1);
        } else if (sortingOption.equals("Page down -> up")){
            BookContentDisplayService.sortBooksByPage(-1);
        }
        if (sortingOption.equals("Year up -> down")){
            BookContentDisplayService.sortBooksByYear(1);
        } else if (sortingOption.equals("Year down -> up")){
            BookContentDisplayService.sortBooksByYear(-1);
        }
        response.sendRedirect("BookSection.jsp");
    }
}
