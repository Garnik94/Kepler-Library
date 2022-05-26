package service;

import model.User;
import model.content.Author;
import model.content.Book;
import service.dao.BookDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ContentDisplayService {

    private static List<Book> bookList = new ArrayList<>();

//    public List<Book> getAllBooks() {
//        try {
//            return BookDAO.getAllBooks();
//        } catch (SQLException throwables) {
//            throw new RuntimeException("Something went wrong");
//        }
//    }

    public static void searchBooksByAuthor(HttpServletRequest request, String currentPage) {
        Author author = new Author(request.getParameter("searchingUser"));
        bookList = BookDAO.getBooksByAuthor(author, currentPage);
    }

}
