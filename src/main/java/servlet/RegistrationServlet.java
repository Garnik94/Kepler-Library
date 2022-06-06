package servlet;

import service.dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistrationServlet")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (password.equals(confirmPassword)) {
            UserDAO.addNewUser(username, password, email);
            request.removeAttribute("mismatchedPasswords");
            response.sendRedirect("Login.jsp");
        } else {
            request.setAttribute("mismatchedPasswords", "true");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Registration.jsp");
            requestDispatcher.forward(request, response);
        }
    }

}
