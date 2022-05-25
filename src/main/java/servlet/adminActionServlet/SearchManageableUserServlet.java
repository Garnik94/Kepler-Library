package servlet.adminActionServlet;

import exceptions.AbsentUserException;
import model.User;
import service.AdminActionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SearchManageableUserServlet")
public class SearchManageableUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            User user = AdminActionService.getUserFromDb(request);
            session.setAttribute("ManageableUser", user);
            if (session.getAttribute("searchingUserNotFound") != null) {
                session.removeAttribute("searchingUserNotFound");
            }
            response.sendRedirect("ManageUser.jsp");
        } catch (AbsentUserException e) {
            if (session.getAttribute("ManageableUser") != null) {
                session.removeAttribute("ManageableUser");
            }
            session.setAttribute("searchingUserNotFound", "true");
            response.sendRedirect("ManageUser.jsp");
        }
    }
}
