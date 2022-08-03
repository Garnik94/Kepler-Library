package controller.permitedActionController;

import service.PermittedActionService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "AddBookServlet")
public class BookAddController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection) servletContext.getAttribute("dbConnection");
        PermittedActionService.addNewBook(request, connection);
        HttpSession session = request.getSession();
        if (session.getAttribute("requiredAddBook") != null) {
            response.sendRedirect("EditBook.jsp");
        } else {
            response.sendRedirect("PermittedProfile.jsp");
        }
    }

}
