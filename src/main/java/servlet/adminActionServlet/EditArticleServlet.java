package servlet.adminActionServlet;

import model.content.Article;
import service.AdminActionService;
import service.ArticleContentDisplayService;
import service.dao.ArticleDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EditArticleServlet")
public class EditArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("ConfirmEditArticle") == null) {
            session.setAttribute("ConfirmEditArticle", true);
            session.removeAttribute("ConfirmEditArticle");
            response.sendRedirect("EditArticle.jsp");
        } else {
            if (request.getParameter("confirmEditArticle") != null &&
                    request.getParameter("ConfirmEditArticle").equals("yes")) {
                Article checkedArticle = (Article) session.getAttribute("checkedArticle");
                AdminActionService.updateArticle(checkedArticle, request);
                for (int i = 0; i < ArticleContentDisplayService.articleList.size(); i++) {
                    if (ArticleContentDisplayService.articleList.get(i).getId() == checkedArticle.getId()) {
                        ArticleContentDisplayService.articleList.set(i, ArticleDAO.getArticlesById(checkedArticle.getId()).get(0));
                        break;
                    }
                }
                session.removeAttribute("checkedArticle");
                session.removeAttribute("ConfirmEditArticle");
                response.sendRedirect("ArticleSection.jsp");
            } else if (request.getParameter("confirmEditArticle") != null &&
                    request.getParameter("confirmEditArticle").equals("no")){
                session.removeAttribute("ConfirmEditArticle");
                response.sendRedirect("EditArticle.jsp");
            } else {
                response.sendRedirect("EditArticle.jsp");
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
