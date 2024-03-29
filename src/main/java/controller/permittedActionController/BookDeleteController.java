package controller.permittedActionController;

import com.google.common.base.Objects;
import model.content.Book;
import service.PermittedActionService;
import service.BookContentDisplayService;

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

@WebServlet(/*value = "/deleteBook",*/ name = "BookDeleteController")
public class BookDeleteController extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection) servletContext.getAttribute("dbConnection");
        HttpSession session = request.getSession();
        if (session.getAttribute("ConfirmDeleteBook") == null) {
            session.setAttribute("ConfirmDeleteBook", "Are you really going to delete book");
            response.sendRedirect("EditBook.jsp?editableBook=" + request.getParameter("editableBook"));
        } else {
            if (request.getParameter("confirmDeleteBook") != null &&
                    Objects.equal(request.getParameter("confirmDeleteBook"), "yes")) {
                Book checkedBook = (Book) session.getAttribute("checkedBook");
                PermittedActionService.deleteBook(checkedBook, connection);
                BookContentDisplayService.deleteBookFromBookList(checkedBook);
                session.removeAttribute("ConfirmDeleteBook");
                session.setAttribute("alreadyDeleted", true);
                response.sendRedirect("BookSection.jsp");
            } else if (request.getParameter("confirmDeleteBook") != null &&
                    Objects.equal(request.getParameter("confirmDeleteBook"), "no")) {
                session.removeAttribute("ConfirmDeleteBook");
                response.sendRedirect("EditBook.jsp?editableBook=" + request.getParameter("editableBook"));
            } else {
                response.sendRedirect("EditBook.jsp?editableBook=" + request.getParameter("editableBook"));
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Error.jsp");
        requestDispatcher.forward(request, response);
    }

}
