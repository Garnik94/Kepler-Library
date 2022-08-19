package controller;

import com.google.common.base.Optional;
import exceptions.AbsentUserException;
import service.UserValidatorService;

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

@WebServlet(/*value = "/welcome",*/ name = "WelcomeController")
public class WelcomeController extends HttpServlet {

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("inputUsername");
        String password = request.getParameter("inputPassword");
        ServletContext servletContext = getServletContext();
        Connection connection = ((Optional<Connection>) servletContext.getAttribute("dbConnection")).get();
        try {
            if (UserValidatorService.validateUser(connection, username, password, request)) {
                session.removeAttribute("searchingOption");
                response.sendRedirect("BookSection.jsp");
            } else {
                session.setAttribute("invalidLogin", "Username or password is wrong");
                response.sendRedirect("Login.jsp");
            }
        } catch (AbsentUserException e) {
            session.setAttribute("invalidLogin", "Username or password is wrong");
            response.sendRedirect("Login.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        session.removeAttribute("ConfirmDeleteBook");
//        response.sendRedirect("BookSection.jsp");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Error.jsp");
        requestDispatcher.forward(request, response);
    }

}
