package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    private static List<Book> getBooks(PreparedStatement preparedStatement) throws SQLException {
        List<Book> books = new ArrayList<>();
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Language language = new Language(resultSet.getString("Language_Name"));
            language.setId(LanguageDAO.getLanguageIdByName(language));
            DocumentType documentType = new DocumentType(resultSet.getString("Document_Type_Name"));
            documentType.setId(DocumentTypeDAO.getDocumentTypeIdByName(documentType));
            Book book = new Book(new Author(resultSet.getString("Author_Name")),
                    resultSet.getString("Title"),
                    new Category(resultSet.getString("Category_Name")),
                    language,
                    resultSet.getInt("Year"),
                    documentType,
                    resultSet.getInt("Pages"),
                    resultSet.getString("Download_Url"));
            book.setId(resultSet.getInt("Book_Id"));
            books.add(book);
        }
        return books;
    }

//    SELECT Id, Col1
//    FROM TableName
//    ORDER BY Id
//    OFFSET 20 ROWS FETCH NEXT 20 ROWS ONLY;

//    SELECT * FROM Customers
//    ORDER BY Country DESC;

//    SELECT *
//    FROM Users
//    ORDER BY (SELECT NULL)
//    OFFSET 1 ROWS FETCH NEXT 2 ROWS ONLY;

    public static List<Book> getBooksByTitle(String title, int offset) {
        try {
            String query = "SELECT * FROM Books " +
                    "INNER JOIN Authors ON Books.Author = Authors.Author_Id \n" +
                    "JOIN Categories ON Books.Category = Categories.Category_Id\n" +
                    "JOIN Languages ON Books.Language = Languages.Language_Id\n" +
                    "JOIN Document_Types ON Books.Document_Type = Document_Types.Document_Type_Id WHERE Title LIKE ?" +
                    " ORDER BY (SELECT NULL) OFFSET " + (9 * offset) + " ROWS FETCH NEXT 9 ROWS ONLY";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, "%" + title + "%");
            return getBooks(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static List<Book> getBooksByAuthor(String searchArg, int offset/*, HttpServletRequest request*/) {
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
                String query = "SELECT * FROM Books " +
                        "INNER JOIN Authors ON Books.Author = Authors.Author_Id \n" +
                        "JOIN Categories ON Books.Category = Categories.Category_Id\n" +
                        "JOIN Languages ON Books.Language = Languages.Language_Id\n" +
                        "JOIN Document_Types ON Books.Document_Type = Document_Types.Document_Type_Id WHERE " + subQuery +
                        " ORDER BY (SELECT NULL) OFFSET " + (9 * offset) + " ROWS FETCH NEXT 9 ROWS ONLY";
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                int i;
                for (i = 0; i < authors.size(); i++) {
                    preparedStatement.setInt(i + 1, authors.get(i).getId());
                }
                return getBooks(preparedStatement);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
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
