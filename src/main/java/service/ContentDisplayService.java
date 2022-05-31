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

    public static void mainSearch() {

//        searchBooksByAuthor();
//        searchBooksByTitle();
    }

    public static void searchBooksByAuthor(String searchingArg, int currentPage) {
        if ((currentPage - 1) % 3 == 0 || currentPage == 1) {
            bookList = BookDAO.getBooksByAuthor(searchingArg, currentPage - 1);
        }
    }

    public static void searchBooksByTitle(String searchingArg, int currentPage) {
        if ((currentPage - 1) % 3 == 0 || currentPage == 1) {
            bookList = BookDAO.getBooksByTitle(searchingArg, currentPage - 1);
        }
    }

}
