package controller.permittedActionController;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
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

@WebServlet(/*value = "/deleteUser",*/ name = "UserDeleteController")
public class UserDeleteController extends HttpServlet {

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Connection connection = ((Optional<Connection>) servletContext.getAttribute("dbConnection")).get();
        HttpSession session = request.getSession();
        if (session.getAttribute("ConfirmDeleteUser") == null) {
            session.setAttribute("ConfirmDeleteUser", true);
            session.removeAttribute("ConfirmPermitUser");
            session.removeAttribute("ConfirmForbidUser");
            response.sendRedirect("ManageUser.jsp");
        } else {
            if (request.getParameter("confirmDeleteUser") != null &&
                    Objects.equal(request.getParameter("confirmDeleteUser"), "yes")) {
                PermittedActionService.deleteUser(request, connection);
                session.removeAttribute("ManageableUser");
                session.removeAttribute("ConfirmDeleteUser");
                response.sendRedirect("ManageUser.jsp");
            } else if (request.getParameter("confirmDeleteUser") != null &&
                    Objects.equal(request.getParameter("confirmDeleteUser"), "no")) {
                session.removeAttribute("ConfirmDeleteUser");
                response.sendRedirect("ManageUser.jsp");
            } else {
                response.sendRedirect("ManageUser.jsp");
            }
        }
    }

}
