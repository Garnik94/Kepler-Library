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
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Registration.jsp");
        if ((!username.isEmpty() ||
                !email.isEmpty() ||
                !password.isEmpty() ||
                !confirmPassword.isEmpty()) &&
                password.equals(confirmPassword)) {
            boolean isUserSuccessfullyAdded = UserDAO.addNewUser(username, password, email);
            if (isUserSuccessfullyAdded) {
                request.removeAttribute("MismatchedPasswords");
                request.removeAttribute("RequiredInputError");
                response.sendRedirect("Login.jsp");
            } else {
                request.setAttribute("UserIsAlreadyExists", "true");
                requestDispatcher.forward(request, response);
            }
        } else if (!password.equals(confirmPassword)) {
            request.setAttribute("MismatchedPasswords", "true");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("RequiredInputError", "true");
            requestDispatcher.forward(request, response);
        }
    }

}
