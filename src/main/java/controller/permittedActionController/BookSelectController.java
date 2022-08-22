package controller.permittedActionController;

import model.content.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(/*value = "/selectBook",*/ name = "BookSelectController")
public class BookSelectController extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("alreadyDeleted");
        int currentEditableBookIndex = Integer.parseInt(request.getParameter("editableBook"));
        Book editableBook = (Book) session.getAttribute(String.valueOf(currentEditableBookIndex));
        session.setAttribute("checkedBook", editableBook);
        response.sendRedirect("EditBook.jsp?editableBook=" + request.getParameter("editableBook"));
    }

}

