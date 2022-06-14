package servlet.adminActionServlet;

import model.content.Book;
import service.AdminActionService;
import service.BookContentDisplayService;
import service.dao.BookDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EditBookServlet")
public class EditBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("ConfirmEditBook") == null) {
            session.setAttribute("ConfirmEditBook", true);
            session.removeAttribute("ConfirmDeleteBook");
            response.sendRedirect("EditBook.jsp?editableBook=" + request.getParameter("editableBook"));
        } else {
            if (request.getParameter("confirmEditBook") != null &&
                    request.getParameter("confirmEditBook").equals("yes")) {
                Book checkedBook = (Book) session.getAttribute("checkedBook");
                AdminActionService.updateBook(checkedBook, request);
                for (int i = 0; i < BookContentDisplayService.bookList.size(); i++) {
                    if (BookContentDisplayService.bookList.get(i).getId() == checkedBook.getId()) {
                        BookContentDisplayService.bookList.set(i, BookDAO.getBookById(checkedBook.getId()).get(0));
                        break;
                    }
                }
                session.removeAttribute("checkedBook");
                session.removeAttribute("ConfirmEditBook");
                response.sendRedirect("BookSection.jsp");
            } else if (request.getParameter("confirmEditBook") != null &&
                    request.getParameter("confirmEditBook").equals("no")){
                session.removeAttribute("ConfirmEditBook");
                response.sendRedirect("EditBook.jsp?editableBook=" + request.getParameter("editableBook"));
            } else {
                response.sendRedirect("EditBook.jsp?editableBook=" + request.getParameter("editableBook"));
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
