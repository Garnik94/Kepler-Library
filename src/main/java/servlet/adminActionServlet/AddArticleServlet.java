package servlet.adminActionServlet;

import model.content.*;
import service.AdminActionService;
import service.dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddArticleServlet")
public class AddArticleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminActionService.addNewArticle(request);
        response.sendRedirect("AdminProfile.jsp");
    }

}
