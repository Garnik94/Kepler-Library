package controller.permitedActionController;

import service.AdminActionService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ForbidUserServlet")
public class UserForbidController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection) servletContext.getAttribute("dbConnection");
        HttpSession session = request.getSession();
        if (session.getAttribute("ConfirmForbidUser") == null) {
            session.setAttribute("ConfirmForbidUser", true);
            session.removeAttribute("ConfirmPermitUser");
            session.removeAttribute("ConfirmDeleteUser");
            response.sendRedirect("ManageUser.jsp");
        } else {
            if (request.getParameter("confirmForbidUser") != null &&
                    request.getParameter("confirmForbidUser").equals("yes")) {
                AdminActionService.forbidUser(request, connection);
                session.removeAttribute("ManageableUser");
                session.removeAttribute("confirmForbidUser");
                response.sendRedirect("ManageUser.jsp");
            } else if (request.getParameter("confirmForbidUser") != null &&
                    request.getParameter("confirmForbidUser").equals("no")) {
                session.removeAttribute("ConfirmForbidUser");
                response.sendRedirect("ManageUser.jsp");
            } else {
                response.sendRedirect("ManageUser.jsp");
            }
        }
    }
}
