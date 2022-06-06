package servlet;

import exceptions.AbsentUserException;
import model.User;
import service.UserValidatorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChooseSectionServlet")
public class WelcomeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("inputUsername");
        String password = request.getParameter("inputPassword");
        try {
            if (UserValidatorService.validateUser(username, password, request)) {
//                session.setAttribute("username", username);
//                session.setAttribute("password", password);
                response.sendRedirect("Welcome.jsp");
            } else {
                request.setAttribute("invalidLogin", "true");
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);
            }
        } catch (AbsentUserException e) {
            request.setAttribute("invalidLogin", "true");
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
        }
    }

//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        if ((session.getAttribute("username") == null || session.getAttribute("password") == null)) {
////            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
////            rd.forward(request, response);
//            response.sendRedirect("Login.jsp");
//        } else {
////            RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
////            rd.forward(request, response);
//            response.sendRedirect("Welcome.jsp");
//        }
//    }

}
