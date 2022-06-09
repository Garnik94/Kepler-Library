package servlet.adminActionServlet;

import exceptions.AbsentUserException;
import model.User;
import service.AdminActionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminActionService.deleteUser(request);
        HttpSession session = request.getSession();
        session.removeAttribute("ManageableUser");
        response.sendRedirect("AdminProfile.jsp");
    }


}
