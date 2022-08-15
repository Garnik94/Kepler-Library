package controller;

import com.google.common.base.Objects;
import service.UserValidatorService;
import service.dao.UserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "RegistrationServlet")
public class RegistrationController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection) servletContext.getAttribute("dbConnection");
        if (UserValidatorService.checkRegistrationRequiredInputs(username, email, password, confirmPassword) &&
                Objects.equal(password, confirmPassword)) {
            boolean isUserSuccessfullyAdded = UserDAO.addNewUser(connection, username, password, email);
            if (isUserSuccessfullyAdded) {
                session.removeAttribute("mismatchedPasswords");
                session.removeAttribute("requiredInputError");
                response.sendRedirect("Login.jsp");
            } else {
                session.setAttribute("userIsAlreadyExists", "User is already exists");
//                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Registration.jsp");
//                requestDispatcher.forward(request, response);
                response.sendRedirect("Registration.jsp");
            }
        } else if (!Objects.equal(password, confirmPassword)) {
            session.setAttribute("mismatchedPasswords", "Mismatched passwords");
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Registration.jsp");
//            requestDispatcher.forward(request, response);
            response.sendRedirect("Registration.jsp");
        } else {
            session.setAttribute("requiredInputError", "All inputs are required");
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Registration.jsp");
//            requestDispatcher.forward(request, response);
            response.sendRedirect("Registration.jsp");
        }
    }

}
