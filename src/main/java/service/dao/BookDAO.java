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

    private static List<Book> getBooks(String authorName, String query) throws SQLException {
        List<Book> books = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, authorName);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            books.add(new Book(
                    AuthorDAO.getAuthorById(resultSet.getInt("Author")),
                    resultSet.getString("Title"),
                    CategoryDAO.getCategoryById(resultSet.getInt("Category")),
                    LanguageDAO.getLanguageById(resultSet.getInt("Language")),
                    resultSet.getInt("Year"),
                    DocumentTypeDAO.getDocumentTypeById(resultSet.getInt("Document_Type")),
                    resultSet.getInt("Pages"))
            );
        }
        return books;
    }

    public static List<Book> getBookByTitle(String title) {
        try {
            String query = "SELECT * FROM Books WHERE Title = ?";
            return getBooks(title, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Book> getBookByAuthor(String authorName) {
        try {
            String query = "SELECT * FROM Books WHERE Name = ?";
            return getBooks(authorName, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addNewUser(String username, String password, String email) {
        try {
            String query = "INSERT INTO Users VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String username) {
        try {
            String query = "DELETE FROM Users WHERE Username = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
