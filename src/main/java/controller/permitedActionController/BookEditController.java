package controller.permitedActionController;

import model.content.Book;
import service.BookContentDisplayService;
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

@WebServlet(name = "EditBookServlet")
public class BookEditController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext servletContext = getServletContext();
        Connection connection = (Connection) servletContext.getAttribute("dbConnection");
        Book checkedBook = (Book) session.getAttribute("checkedBook");
        PermittedActionService.updateBook(checkedBook, request, connection);
        BookContentDisplayService.updateBookFromBookList(checkedBook, connection);
        session.removeAttribute("checkedBook");
        response.sendRedirect("BookSection.jsp");
    }

}
