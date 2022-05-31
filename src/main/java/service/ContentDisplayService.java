package service;

import model.SearchingOption;
import model.content.Book;
import service.dao.BookDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    public static void mainSearch(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SearchingOption searchingOption = (SearchingOption) session.getAttribute("searchingOption");
//        if (searchingOption.get)
//            if (searchingOption.isAuthorSelected() && searchingOption.isTitleSelected()) {
//
//            }

    }

//    public static void searchBooksByAuthorAndTitle(String searchingArgument, int currentPage) {
//        if ((currentPage - 1) % 3 == 0 || currentPage == 1) {
//            bookList = BookDAO.getBooksByAuthor(author, currentPage - 1);
//        }
//    }

    public static void searchBooksByAuthor(String author, int currentPage) {
        if ((currentPage - 1) % 3 == 0 || currentPage == 1) {
            bookList = BookDAO.getBooksByAuthor(author, currentPage - 1);
        }
    }

    public static void searchBooksByTitle(String title, int currentPage) {
        if ((currentPage - 1) % 3 == 0 || currentPage == 1) {
            bookList = BookDAO.getBooksByTitle(title, currentPage - 1);
        }
    }

}
