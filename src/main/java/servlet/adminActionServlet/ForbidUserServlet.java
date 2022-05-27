package servlet.adminActionServlet;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import service.AdminActionService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ForbidUserServlet")
public class ForbidUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminActionService.forbidUser(request);
        HttpSession session = request.getSession();
        session.removeAttribute("ManageableUser");
        response.sendRedirect("AdminProfile.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
