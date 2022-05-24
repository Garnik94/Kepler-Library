package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.Article;
import model.content.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static List<Article> getArticles(String searchParameter, String query) throws SQLException {
        List<Article> articles = new ArrayList<>();
        PreparedStatement preparedStatement = getConnection().prepareStatement(query);
        preparedStatement.setString(1, searchParameter);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Article article = new Article(
                    AuthorDAO.getAuthorById(resultSet.getInt("Author")),
                    resultSet.getString("Title"),
                    CategoryDAO.getCategoryById(resultSet.getInt("Category")),
                    LanguageDAO.getLanguageById(resultSet.getInt("Language")),
                    resultSet.getInt("Year"),
                    DocumentTypeDAO.getDocumentTypeById(resultSet.getInt("Document_Type")),
                    JournalDAO.getJournalById(resultSet.getInt("Journal")),
                    resultSet.getString("Download_Url"));
            article.setId(resultSet.getInt("ID"));
            articles.add(article);
        }
        return articles;
    }

    public static List<Article> getArticlesByTitle(String title) {
        try {
            String query = "SELECT * FROM Articles WHERE Title = ?";
            return getArticles(title, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Article> getArticlesByAuthor(Author author) {
        try {
            String query = "SELECT * FROM Articles WHERE Name = ?";
            return getArticles(author.getAuthorName(), query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addNewArticle(Article article) {
        int authorId = AuthorDAO.addNewAuthor(article.getAuthor());
        String title = article.getTitle();
        int categoryId = CategoryDAO.addNewCategory(article.getCategory());
        int languageId = LanguageDAO.addNewLanguage(article.getLanguage());
        int year = article.getYear();
        int documentTypeId = DocumentTypeDAO.addNewDocumentType(article.getDocumentType());
        int journal = JournalDAO.getJournalIdByName(article.getJournal());
        String downloadUrl = article.getDownloadUrl();
        try {
            String query = "INSERT INTO Articles VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, authorId);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, categoryId);
            preparedStatement.setInt(4, languageId);
            preparedStatement.setInt(5, year);
            preparedStatement.setInt(6, documentTypeId);
            preparedStatement.setInt(7, journal);
            preparedStatement.setString(8, downloadUrl);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteArticle(Article article) {
        try {
            String query = "DELETE FROM Articles WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, article.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
