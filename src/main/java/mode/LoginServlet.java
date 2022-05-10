package mode;

import service.MainDynamicPageService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MainDynamicPageService.onLoginPage();
        HttpSession session = request.getSession();
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MainDynamicPageService.onLoginPage();
        request.setAttribute("invalidLogin", "false");
        HttpSession session = request.getSession();
        session.invalidate();
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
    }

}
