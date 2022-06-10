package servlet;

import service.ArticleContentDisplayService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SortArticleContentServlet")
public class SortArticleContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sortingOption = request.getParameter("sortingOption");
        if (sortingOption.equals("recentlyAdded")){
            ArticleContentDisplayService.sortArticlesByRecentlyAdded();
        }
        if (sortingOption.equals("bookTitleUp")){
            ArticleContentDisplayService.sortArticlesByTitle(1);
        } else if (sortingOption.equals("bookTitleDown")){
            ArticleContentDisplayService.sortArticlesByTitle(-1);
        }
        if (sortingOption.equals("bookPageUp")){
            ArticleContentDisplayService.sortArticlesByJournal(1);
        } else if (sortingOption.equals("bookPageDown")){
            ArticleContentDisplayService.sortArticlesByJournal(-1);
        }
        if (sortingOption.equals("bookYearUp")){
            ArticleContentDisplayService.sortArticlesByYear(1);
        } else if (sortingOption.equals("bookYearDown")){
            ArticleContentDisplayService.sortArticlesByYear(-1);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ArticleSection.jsp");
        requestDispatcher.forward(request, response);
    }

}
