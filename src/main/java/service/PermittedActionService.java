package service;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import exceptions.AbsentUserException;
import model.User;
import model.content.*;
import service.dao.BookDAO;
import service.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

public class PermittedActionService {

    private void addContent(HttpServletRequest request, HttpServletResponse response) {

    }

    public static boolean checkRequiredInputs(String... inputs) {
        for (String input : inputs) {
            if (input.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static void addNewBook(HttpServletRequest request, Connection connection) throws IOException {
        Preconditions.checkNotNull(connection);
            String author = request.getParameter("author");
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            String language = request.getParameter("language");
            String year = request.getParameter("year");
            String documentType = request.getParameter("documentType");
            String pages = request.getParameter("pages");
            String downloadUrl = request.getParameter("downloadUrl");
        if(!checkRequiredInputs(author, title, category, language, year, documentType, pages, downloadUrl)) {
            BookDAO.addNewBook(connection, new Book(new Author(author),
                    title,
                    new Category(category),
                    new Language(language),
                    Integer.parseInt(year),
                    new DocumentType(documentType),
                    Integer.parseInt(pages),
                    downloadUrl));
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("requiredAddBook", "All inputs are required");
        }
    }

    public static void updateBook(Book book, HttpServletRequest request, Connection connection) {
        Preconditions.checkNotNull(book);
        Preconditions.checkNotNull(connection);
        String author = request.getParameter("editAuthor");
        String title = request.getParameter("editTitle");
        String category = request.getParameter("editCategory");
        String language = request.getParameter("editLanguage");
        String year = request.getParameter("editYear");
        String documentType = request.getParameter("editDocumentType");
        String pages = request.getParameter("editPages");
        String downloadUrl = request.getParameter("editDownloadUrl");
        if (!checkRequiredInputs(author, title, category, language, year, documentType, pages, downloadUrl)) {
            BookDAO.updateBook(connection,
                    book,
                    new Author(author),
                    title,
                    new Category(category),
                    new Language(language),
                    Integer.parseInt(year),
                    new DocumentType(documentType),
                    Integer.parseInt(pages),
                    downloadUrl);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("requiredUpdateBook", "All inputs are required");
        }
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
