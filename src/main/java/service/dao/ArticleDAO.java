package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.*;

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

    private static List<Article> getArticles(PreparedStatement preparedStatement) throws SQLException {
        List<Article> articles = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Category category = new Category(resultSet.getString("Category_Name"));
            category.setId(CategoryDAO.getCategoryIdByName(category));
            Language language = new Language(resultSet.getString("Language_Name"));
            language.setId(LanguageDAO.getLanguageIdByName(language));
            DocumentType documentType = new DocumentType(resultSet.getString("Document_Type_Name"));
            documentType.setId(DocumentTypeDAO.getDocumentTypeIdByName(documentType));
            Author author = new Author(resultSet.getString("Author_Name"));
            author.setId(AuthorDAO.getAuthorIdByName(author));
            Journal journal = new Journal(resultSet.getString("Journal_Name"));
            journal.setId(JournalDAO.getJournalIdByName(journal));
            Article article = new Article(author,
                    resultSet.getString("Title"),
                    category,
                    language,
                    resultSet.getInt("Year"),
                    documentType,
                    journal,
                    resultSet.getString("Download_Url"));
            article.setId(resultSet.getInt("Article_Id"));
            articles.add(article);
        }
        return articles;
    }

    public static List<Article> getArticlesById(int id) {
        try {
            String query = "SELECT * FROM Articles " +
                    "INNER JOIN Authors ON Articles.Author = Authors.Author_Id \n" +
                    "JOIN Categories ON Articles.Category = Categories.Category_Id\n" +
                    "JOIN Languages ON Articles.Language = Languages.Language_Id\n" +
                    "JOIN Document_Types ON Articles.Document_Type = Document_Types.Document_Type_Id " +
                    "JOIN Journals ON Articles.Journal = Journals.Journal_Id " +
                    "WHERE Article_Id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(id));
            return getArticles(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    public static List<Article> getArticlesByTitle(String title) {
        try {
            String query = "SELECT * FROM Articles " +
                    "INNER JOIN Authors ON Articles.Author = Authors.Author_Id \n" +
                    "JOIN Categories ON Articles.Category = Categories.Category_Id\n" +
                    "JOIN Languages ON Articles.Language = Languages.Language_Id\n" +
                    "JOIN Document_Types ON Articles.Document_Type = Document_Types.Document_Type_Id " +
                    "JOIN Journals ON Articles.Journal = Journals.Journal_Id " +
                    "WHERE Article_Id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, "%" + title + "%");
            return getArticles(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Article> getArticlesByAuthor(String searchArg) {
        List<Author> authors = AuthorDAO.getAuthorsByCoincidence(searchArg);
        if (!authors.isEmpty()) {
            try {
                StringBuilder subQuery = new StringBuilder();
                for (int i = 0; i < authors.size(); i++) {
                    if (i == 0) {
                        subQuery.append("Author = ?");
                    } else {
                        subQuery.append(" OR Author = ? ");
                    }
                }
                String query = "SELECT * FROM Articles " +
                        "INNER JOIN Authors ON Articles.Author = Authors.Author_Id \n" +
                        "JOIN Categories ON Articles.Category = Categories.Category_Id\n" +
                        "JOIN Languages ON Articles.Language = Languages.Language_Id\n" +
                        "JOIN Document_Types ON Articles.Document_Type = Document_Types.Document_Type_Id " +
                        "JOIN Journals ON Articles.Journal = Journals.Journal_Id WHERE " + subQuery;
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                int i;
                for (i = 0; i < authors.size(); i++) {
                    preparedStatement.setInt(i + 1, authors.get(i).getId());
                }
                return getArticles(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    public static void addNewArticle(Article article) {
        int authorId = AuthorDAO.addNewAuthor(article.getAuthor());
        String title = article.getTitle();
        int categoryId = CategoryDAO.addNewCategory(article.getCategory());
        int languageId = LanguageDAO.addNewLanguage(article.getLanguage());
        int year = article.getYear();
        int documentTypeId = DocumentTypeDAO.addNewDocumentType(article.getDocumentType());
        int journalId = JournalDAO.addNewJournal(article.getJournal());
        String downloadUrl = article.getDownloadUrl();
        try {
            String query = "INSERT INTO Books VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, authorId);
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, categoryId);
            preparedStatement.setInt(4, languageId);
            preparedStatement.setInt(5, year);
            preparedStatement.setInt(6, documentTypeId);
            preparedStatement.setInt(7, journalId);
            preparedStatement.setString(8, downloadUrl);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateArticle(Article article,
                                     Author author,
                                     String title,
                                     Category category,
                                     Language language,
                                     int year,
                                     DocumentType documentType,
                                     Journal journal,
                                     String downloadUrl) {
        try {
            category.setId(CategoryDAO.addNewCategory(category));
            language.setId(LanguageDAO.addNewLanguage(language));
            documentType.setId(DocumentTypeDAO.addNewDocumentType(documentType));
            author.setId(AuthorDAO.addNewAuthor(author));
            journal.setId(JournalDAO.addNewJournal(journal));
            String query = "UPDATE Articles SET Author = ?, Title = ?, Category = ?, Language = ?, Year = ?, Document_Type = ?, Journal = ?, Download_Url = ? WHERE Article_Id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, author.getId());
            preparedStatement.setString(2, title);
            preparedStatement.setInt(3, category.getId());
            preparedStatement.setInt(4, language.getId());
            preparedStatement.setInt(5, year);
            preparedStatement.setInt(6, documentType.getId());
            preparedStatement.setInt(7, journal.getId());
            preparedStatement.setString(8, downloadUrl);
            preparedStatement.setInt(9, article.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteArticle(Article article) {
        try {
            String query = "DELETE FROM Articles WHERE Article_Id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, article.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
