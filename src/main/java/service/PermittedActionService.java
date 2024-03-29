package service;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import exceptions.AbsentUserException;
import model.User;
import model.content.*;
import service.dao.BookDAO;
import service.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class PermittedActionService {

    public static void addNewBook(HttpServletRequest request, Connection connection) throws IOException {
        Preconditions.checkNotNull(connection);
        Author author = new Author(request.getParameter("author"));
        String title = request.getParameter("title");
        Category category = new Category(request.getParameter("category"));
        Language language = new Language(request.getParameter("language"));
        int year = request.getParameter("year").isEmpty() ? 0 : Integer.parseInt(request.getParameter("year"));
        DocumentType documentType = new DocumentType(request.getParameter("documentType"));
        int pages = request.getParameter("pages").isEmpty() ? 0 : Integer.parseInt(request.getParameter("pages"));
        String downloadUrl = request.getParameter("downloadUrl");
        BookDAO.addNewBook(connection, new Book(author, title, category, language, year, documentType, pages, downloadUrl));
    }

    public static void updateBook(Book book, HttpServletRequest request, Connection connection) {
        Preconditions.checkNotNull(book);
        Preconditions.checkNotNull(connection);
        Author author = new Author(request.getParameter("editAuthor"));
        String title = request.getParameter("editTitle");
        Category category = new Category(request.getParameter("editCategory"));
        Language language = new Language(request.getParameter("editLanguage"));
        int year = request.getParameter("editYear").isEmpty() ? 0 : Integer.parseInt(request.getParameter("editYear"));
        DocumentType documentType = new DocumentType(request.getParameter("editDocumentType"));
        int pages = request.getParameter("editPages").isEmpty() ? 0 : Integer.parseInt(request.getParameter("editPages"));
        String downloadUrl = request.getParameter("editDownloadUrl");
        BookDAO.updateBook(connection, book, author, title, category, language, year, documentType, pages, downloadUrl);
    }

    public static void deleteBook(Book book, Connection connection) {
        Preconditions.checkNotNull(book);
        Preconditions.checkNotNull(connection);
        BookDAO.deleteBook(connection, book);
    }

    public static User getUserFromDb(HttpServletRequest request, Connection connection) throws AbsentUserException {
        Preconditions.checkNotNull(connection);
        User user = new User(request.getParameter("searchingUser"));
        return UserDAO.getUser(connection, user);
    }

    public static void deleteUser(HttpServletRequest request, Connection connection) {
        Preconditions.checkNotNull(connection);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("ManageableUser");
        UserDAO.deleteUser(connection, user);
    }

    public static void permitUser(HttpServletRequest request, Connection connection) {
        Preconditions.checkNotNull(connection);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("ManageableUser");
        UserDAO.permitUser(connection, user);
    }

    public static void forbidUser(HttpServletRequest request, Connection connection) {
        Preconditions.checkNotNull(connection);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("ManageableUser");
        UserDAO.forbidUser(connection, user);
    }

}
