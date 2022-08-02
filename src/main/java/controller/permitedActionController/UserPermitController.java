package controller.permitedActionController;

import service.PermittedActionService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "PermitUserServlet")
public class UserPermitController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection) servletContext.getAttribute("dbConnection");
        HttpSession session = request.getSession();
        if (session.getAttribute("ConfirmPermitUser") == null) {
            session.setAttribute("ConfirmPermitUser", true);
            session.removeAttribute("ConfirmForbidUser");
            session.removeAttribute("ConfirmDeleteUser");
            response.sendRedirect("ManageUser.jsp");
        } else {
            if (request.getParameter("confirmPermitUser") != null &&
                    request.getParameter("confirmPermitUser").equals("yes")) {
                PermittedActionService.permitUser(request, connection);
                session.removeAttribute("ManageableUser");
                session.removeAttribute("ConfirmPermitUser");
                response.sendRedirect("ManageUser.jsp");
            } else if (request.getParameter("confirmPermitUser") != null &&
                    request.getParameter("confirmPermitUser").equals("no")) {
                session.removeAttribute("ConfirmPermitUser");
                response.sendRedirect("ManageUser.jsp");
            } else {
                response.sendRedirect("ManageUser.jsp");
            }
        }
    }
}
