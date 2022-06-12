package servlet.adminActionServlet;

import model.content.Article;
import model.content.Book;
import service.AdminActionService;
import service.ArticleContentDisplayService;
import service.BookContentDisplayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

@WebServlet(name = "DeleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("ConfirmDeleteArticle") == null) {
            session.setAttribute("ConfirmDeleteArticle", true);
            session.removeAttribute("ConfirmEditArticle");
            response.sendRedirect("EditArticle.jsp");
        } else {
            if (request.getParameter("confirmDeleteArticle") != null &&
                    request.getParameter("confirmDeleteArticle").equals("yes")) {
                Article checkedArticle = (Article) session.getAttribute("checkedArticle");
                AdminActionService.deleteArticle(checkedArticle);
                Iterator<Article> iterator = ArticleContentDisplayService.articleList.iterator();
                while (iterator.hasNext()) {
                    Article article = iterator.next();
                    if (article.getId() == checkedArticle.getId()) {
                        iterator.remove();
                        break;
                    }
                }
                session.removeAttribute("checkedArticle");
                session.removeAttribute("ConfirmDeleteArticle");
                response.sendRedirect("ArticleSection.jsp");
            } else if (request.getParameter("confirmDeleteArticle") != null &&
                    request.getParameter("confirmDeleteArticle").equals("no")){
                session.removeAttribute("ConfirmDeleteArticle");
                response.sendRedirect("EditArticle.jsp");
            } else {
                response.sendRedirect("EditArticle.jsp");
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
