package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    private static List<Book> getBooks(String searchParameter, String query) throws SQLException {
        List<Book> books = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, searchParameter);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Book book = new Book(AuthorDAO.getAuthorById(resultSet.getInt("Author")),
                    resultSet.getString("Title"),
                    CategoryDAO.getCategoryById(resultSet.getInt("Category")),
                    LanguageDAO.getLanguageById(resultSet.getInt("Language")),
                    resultSet.getInt("Year"),
                    DocumentTypeDAO.getDocumentTypeById(resultSet.getInt("Document_Type")),
                    resultSet.getInt("Pages"),
                    resultSet.getString("Download_Url"));
            book.setId(resultSet.getInt("ID"));
            books.add(book);
        }
        return books;
    }

    public static List<Book> getBooksByTitle(String title) {
        try {
            String query = "SELECT * FROM Books WHERE Title = ?";
            return getBooks(title, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Book> getBooksByAuthor(Author author) {
        try {
            String query = "SELECT * FROM Books WHERE Name = ?";
            return getBooks(author.getAuthorName(), query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addNewBook(Book book) {
        int authorId = AuthorDAO.addNewAuthor(book.getAuthor());
        String title = book.getTitle();
        int categoryId = CategoryDAO.addNewCategory(book.getCategory());
        int languageId = LanguageDAO.addNewLanguage(book.getLanguage());
        int year = book.getYear();
        int documentTypeId = DocumentTypeDAO.addNewDocumentType(book.getDocumentType());
        int pages = book.getPages();
        String downloadUrl = book.getDownloadUrl();
        try {
            String query = "INSERT INTO Books VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, authorId);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, categoryId);
            preparedStatement.setInt(4, languageId);
            preparedStatement.setInt(5, year);
            preparedStatement.setInt(6, documentTypeId);
            preparedStatement.setInt(7, pages);
            preparedStatement.setString(8, downloadUrl);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBook(Book book) {
        try {
            String query = "DELETE FROM Books WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
