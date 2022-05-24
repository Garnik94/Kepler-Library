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

@WebServlet(name = "SearchDeletableUserServlet")
public class SearchDeletableUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            User user = AdminActionService.getUserFromDb(request);
//            request.setAttribute("deletableUser", user);
            session.setAttribute("deletableUser", user);
            if(session.getAttribute("searchingUserNotFound") != null){
                session.removeAttribute("searchingUserNotFound");
            }
            response.sendRedirect("DeleteUser.jsp");
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("DeleteUser.jsp");
//            requestDispatcher.forward(request, response);
        } catch (AbsentUserException e) {
            if(session.getAttribute("deletableUser") != null){
                session.removeAttribute("deletableUser");
            }
            session.setAttribute("searchingUserNotFound", "true");
            response.sendRedirect("DeleteUser.jsp");
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("DeleteUser.jsp");
//            requestDispatcher.forward(request, response);
        }
    }
}
