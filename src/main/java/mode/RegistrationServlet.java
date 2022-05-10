package mode;

import service.MainDynamicPageService;
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
//        MainDynamicPageService.onRegistrationPage();
//        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
//        rd.forward(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        UserDAO.addNewUser(username, password, email);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MainDynamicPageService.onRegistrationPage();
        RequestDispatcher rd = request.getRequestDispatcher("Registration.jsp");
        rd.forward(request, response);
    }
}
