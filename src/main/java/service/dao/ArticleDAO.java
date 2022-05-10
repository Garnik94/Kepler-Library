package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.Article;
import model.content.Book;

import java.sql.*;

public class ArticleDAO {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static Article getArticleByAuthor(String authorName) {
        try {
            String query = "SELECT * FROM Articles WHERE Name = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, authorName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Article(
                        AuthorDAO.getAuthorById(resultSet.getInt("Author")),
                        resultSet.getString("Title"),
                        CategoryDAO.getCategoryById(resultSet.getInt("Category")),
                        LanguageDAO.getLanguageById(resultSet.getInt("Language")),
                        resultSet.getInt("Year"),
                        DocumentTypeDAO.getDocumentTypeById(resultSet.getInt("Document_Type")),
                        JournalDAO.getJournalById(resultSet.getInt("Journal"))
                );
            }
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
