package servlet;

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
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection) servletContext.getAttribute("dbConnection");
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Registration.jsp");
        if ((!username.isEmpty() ||
                !email.isEmpty() ||
                !password.isEmpty() ||
                !confirmPassword.isEmpty()) &&
                password.equals(confirmPassword)) {
            boolean isUserSuccessfullyAdded = UserDAO.addNewUser(connection, username, password, email);
            if (isUserSuccessfullyAdded) {
                session.removeAttribute("MismatchedPasswords");
                session.removeAttribute("RequiredInputError");
                response.sendRedirect("Login.jsp");
            } else {
                session.setAttribute("UserIsAlreadyExists", "true");
                response.sendRedirect("Registration.jsp");
            }
        } else if (!password.equals(confirmPassword)) {
            session.setAttribute("MismatchedPasswords", "true");
            response.sendRedirect("Registration.jsp");
        } else {
            session.setAttribute("RequiredInputError", "true");
            response.sendRedirect("Registration.jsp");
        }
    }

}
