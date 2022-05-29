package service;

import model.User;
import model.content.Author;
import model.content.Book;
import service.dao.BookDAO;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class ContentDisplayService {

    public static List<Book> bookList = new ArrayList<>();

//    public List<Book> getAllBooks() {
//        try {
//            return BookDAO.getAllBooks();
//        } catch (SQLException throwables) {
//            throw new RuntimeException("Something went wrong");
//        }
//    }

    public static void searchBooksByAuthor(String searchingArg, int currentPage) {
//        Author author = new Author(request.getParameter("searchingUser"));
        //TODO: logic of caching
        if (currentPage % 3 == 0 || currentPage == 1) {
            bookList = BookDAO.getBooksByAuthor(searchingArg, currentPage);
        }
    }

}
