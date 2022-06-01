package service;

import model.SearchingOption;
import model.content.Book;
import model.content.Category;
import model.content.DocumentType;
import model.content.Language;
import service.dao.BookDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
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

    public static void mainSearch(HttpServletRequest request, int currentPage) {
        HttpSession session = request.getSession();
//        Category category;
//        if (request.getParameter("selectedCategory") != null &&
//                request.getParameter("selectedCategory").equals("blankCategory")) {
//            category = null;
//        } else {
//            category = new Category(request.getParameter("selectedCategory"));
//        }
//        DocumentType documentType;
//        if (request.getParameter("selectedDocumentType") != null &&
//                request.getParameter("selectedDocumentType").equals("blankDocumentType")) {
//            documentType = null;
//        } else {
//            documentType = new DocumentType(request.getParameter("blankDocumentType"));
//        }
//        Language language;
//        if (request.getParameter("selectedLanguage") != null &&
//                request.getParameter("selectedLanguage").equals("blankLanguage")) {
//            language = null;
//        } else {
//            language = new Language(request.getParameter("selectedLanguage"));
//        }
//        SearchingOption searchingOption = new SearchingOption(request.getParameter("searchBook"),
//                request.getParameter("searchBy"),
//                category,
//                documentType,
//                language);
        SearchingOption searchingOption = (SearchingOption) session.getAttribute("searchingOption");

        if (searchingOption.getSearchBy().equals("Author")){
            searchBooksByAuthor(searchingOption.getInputSearchOption(), currentPage);
        } else {
            searchBooksByTitle(searchingOption.getInputSearchOption(), currentPage);
        }
        filterBooksByLanguage(searchingOption.getLanguage());
        filterBooksByDocumentType(searchingOption.getDocumentType());
    }

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

    public static void filterBooksByLanguage(Language language) {
        if (language != null) {
            Iterator<Book> iterator = bookList.iterator();
            while (iterator.hasNext()) {
                Book book = iterator.next();
                if (book.getLanguage().getId() != language.getId()) {
                    iterator.remove();
                }
            }
        }
    }

    public static void filterBooksByDocumentType(DocumentType documentType) {
        if (documentType != null) {
            Iterator<Book> iterator = bookList.iterator();
            while (iterator.hasNext()) {
                Book book = iterator.next();
                if (book.getDocumentType().getId() != documentType.getId()) {
                    iterator.remove();
                }
            }
        }
    }

}
