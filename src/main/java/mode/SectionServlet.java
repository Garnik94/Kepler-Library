package mode;

import exceptions.AbsentUserException;
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
public class SectionServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("inputUsername");
        String password = request.getParameter("inputPassword");
        request.setAttribute("invalidLogin", "false");
        try {
            if (new UserValidatorService().validateUser(username, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                RequestDispatcher rd = request.getRequestDispatcher("Section.jsp");
                rd.forward(request, response);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if ((session.getAttribute("username") == null || session.getAttribute("password") == null)) {
            RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("Section.jsp");
            rd.forward(request, response);
        }
    }
}
