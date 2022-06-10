package servlet;

import service.BookContentDisplayService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SortContentServlet")
public class SortBookContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortingOption = request.getParameter("sortingOption");
        if (sortingOption.equals("recentlyAdded")){
            BookContentDisplayService.sortBooksByRecentlyAdded();
        }
        if (sortingOption.equals("bookTitleUp")){
            BookContentDisplayService.sortBooksByTitle(1);
        } else if (sortingOption.equals("bookTitleDown")){
            BookContentDisplayService.sortBooksByTitle(-1);
        }
        if (sortingOption.equals("bookPageUp")){
            BookContentDisplayService.sortBooksByPage(1);
        } else if (sortingOption.equals("bookPageDown")){
            BookContentDisplayService.sortBooksByPage(-1);
        }

        if (sortingOption.equals("bookYearUp")){
            BookContentDisplayService.sortBooksByYear(1);
        } else if (sortingOption.equals("bookYearDown")){
            BookContentDisplayService.sortBooksByYear(-1);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
        requestDispatcher.forward(request, response);
    }
}
