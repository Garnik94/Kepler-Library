package controller.permitedActionController;

import model.content.Book;
import service.PermittedActionService;
import service.BookContentDisplayService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "DeleteBookServlet")
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
                    request.getParameter("confirmDeleteBook").equals("yes")) {
                Book checkedBook = (Book) session.getAttribute("checkedBook");
                PermittedActionService.deleteBook(checkedBook, connection);
                BookContentDisplayService.deleteBookFromBookList(checkedBook);
                session.removeAttribute("checkedBook");
                session.removeAttribute("ConfirmDeleteBook");
                response.sendRedirect("BookSection.jsp");
            } else if (request.getParameter("confirmDeleteBook") != null &&
                    request.getParameter("confirmDeleteBook").equals("no")) {
                session.removeAttribute("ConfirmDeleteBook");
                response.sendRedirect("EditBook.jsp?editableBook=" + request.getParameter("editableBook"));
            } else {
                response.sendRedirect("EditBook.jsp?editableBook=" + request.getParameter("editableBook"));
            }
        }
    }

}
