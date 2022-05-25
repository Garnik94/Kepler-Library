package servlet.adminActionServlet;

import service.AdminActionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddBookServlet")
public class AddBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminActionService.addNewBook(request);
        response.sendRedirect("AdminProfile.jsp");
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.sendRedirect("AddBook.jsp");
//    }
}
