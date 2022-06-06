package servlet.adminActionServlet;

import model.content.Book;
import service.AdminActionService;
import service.ContentDisplayService;
import service.dao.BookDAO;

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
        Book checkedBook = (Book) session.getAttribute("checkedBook");
        AdminActionService.deleteBook(checkedBook);
        Iterator<Book> iterator = ContentDisplayService.bookList.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == checkedBook.getId()) {
                iterator.remove();
                break;
            }
        }
        response.sendRedirect("BookSection.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
