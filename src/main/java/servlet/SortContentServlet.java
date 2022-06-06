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

@WebServlet(name = "SortContentServlet")
public class SortContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortingOption = request.getParameter("sortingOption");
//        int currentPage = Integer.parseInt(request.getParameter("page"));
        if (sortingOption.equals("recentlyAdded")){
            ContentDisplayService.sortBooksByRecentlyAdded();
        }
        if (sortingOption.equals("bookTitleUp")){
            ContentDisplayService.sortBooksByTitle(1);
        } else if (sortingOption.equals("bookTitleDown")){
            ContentDisplayService.sortBooksByTitle(-1);
        }
        if (sortingOption.equals("bookPageUp")){
            ContentDisplayService.sortBooksByPage(1);
        } else if (sortingOption.equals("bookPageDown")){
            ContentDisplayService.sortBooksByPage(-1);
        }

        if (sortingOption.equals("bookYearUp")){
            ContentDisplayService.sortBooksByYear(1);
        } else if (sortingOption.equals("bookYearDown")){
            ContentDisplayService.sortBooksByYear(-1);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("BookSection.jsp");
        requestDispatcher.forward(request, response);
    }
}
