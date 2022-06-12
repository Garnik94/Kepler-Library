package servlet.adminActionServlet;

import model.content.Book;
import service.AdminActionService;
import service.BookContentDisplayService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

@WebServlet(name = "DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("ConfirmDeleteBook") == null) {
            session.setAttribute("ConfirmDeleteBook", true);
            session.removeAttribute("ConfirmEditBook");
            response.sendRedirect("EditBook.jsp");
        } else {
            if (request.getParameter("confirmDeleteBook") != null &&
                    request.getParameter("confirmDeleteBook").equals("yes")) {
                Book checkedBook = (Book) session.getAttribute("checkedBook");
                AdminActionService.deleteBook(checkedBook);
                Iterator<Book> iterator = BookContentDisplayService.bookList.iterator();
                while (iterator.hasNext()) {
                    Book book = iterator.next();
                    if (book.getId() == checkedBook.getId()) {
                        iterator.remove();
                        break;
                    }
                }
                session.removeAttribute("checkedBook");
                session.removeAttribute("ConfirmDeleteBook");
                response.sendRedirect("BookSection.jsp");
            } else if (request.getParameter("confirmDeleteBook") != null &&
                    request.getParameter("confirmDeleteBook").equals("no")){
                session.removeAttribute("ConfirmDeleteBook");
                response.sendRedirect("EditBook.jsp");
            } else {
                response.sendRedirect("EditBook.jsp");
            }
        }
    }

}
