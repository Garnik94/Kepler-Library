package controller.permittedActionController;

import com.google.common.base.Objects;
import service.PermittedActionService;

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

@WebServlet(/*value = "/permitUser",*/ name = "UserPermitController")
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
                    Objects.equal(request.getParameter("confirmPermitUser"), "yes")) {
                PermittedActionService.permitUser(request, connection);
                session.removeAttribute("ManageableUser");
                session.removeAttribute("ConfirmPermitUser");
                response.sendRedirect("ManageUser.jsp");
            } else if (request.getParameter("confirmPermitUser") != null &&
                    Objects.equal(request.getParameter("confirmPermitUser"), "no")) {
                session.removeAttribute("ConfirmPermitUser");
                response.sendRedirect("ManageUser.jsp");
            } else {
                response.sendRedirect("ManageUser.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Error.jsp");
        requestDispatcher.forward(request, response);
    }

}
