package service;

import exceptions.AbsentUserException;
import model.User;
import model.content.*;
import service.dao.ArticleDAO;
import service.dao.BookDAO;
import service.dao.UserDAO;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AdminActionService {

    private void addContent(HttpServletRequest request, HttpServletResponse response) {

    }

    public static void addNewBook(HttpServletRequest request) throws IOException {
        Author author = new Author(request.getParameter("author"));
        String title = request.getParameter("title");
        Category category = new Category(request.getParameter("category"));
        Language language = new Language(request.getParameter("language"));
        int year = Integer.parseInt(request.getParameter("year"));
        DocumentType documentType = new DocumentType(request.getParameter("documentType"));
        int pages = Integer.parseInt(request.getParameter("pages"));
        String downloadUrl = request.getParameter("downloadUrl");
        BookDAO.addNewBook(new Book(author, title, category, language, year, documentType, pages, downloadUrl));
        ;
    }

    public static void addNewArticle(HttpServletRequest request) throws IOException {
        Author author = new Author(request.getParameter("author"));
        String title = request.getParameter("title");
        Category category = new Category(request.getParameter("category"));
        Language language = new Language(request.getParameter("language"));
        int year = Integer.parseInt(request.getParameter("year"));
        DocumentType documentType = new DocumentType(request.getParameter("documentType"));
        Journal journal = new Journal(request.getParameter("journal"));
        String downloadUrl = request.getParameter("downloadUrl");
        ArticleDAO.addNewArticle(new Article(author, title, category, language, year, documentType, journal, downloadUrl));
    }

    public static User getUserFromDb(HttpServletRequest request) throws AbsentUserException {
        User user = new User(request.getParameter("searchingUser"));
        return UserDAO.getUser(user);
    }

    public static void deleteUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("ManageableUser");
        UserDAO.deleteUser(user);
    }

    public static void permitUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("ManageableUser");
        UserDAO.permitUser(user);
    }

    public static void forbidUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("ManageableUser");
        UserDAO.forbidUser(user);
    }


}