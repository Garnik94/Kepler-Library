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

    public static List<Book> getBooksByTitle(String title, String offset) {
        try {
            int offsetRows = Integer.parseInt(offset) * 10;
            String query = "SELECT * FROM Books WHERE Title LIKE " + "'%" + "?" + "%'"; /*? OFFSET " + offsetRows + " FETCH NEXT 10 ROWS ONLY";*/
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            return getBooks(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Book> getBooksByAuthor(String searchArg, String offset, HttpServletRequest request) {
        List<Author> authors = AuthorDAO.getAuthorsByCoincidence(searchArg);
        if (!authors.isEmpty()) {
            PreparedStatement preparedStatement;
            try {
                StringBuilder subQuery = new StringBuilder();
                for (int i = 0; i < authors.size(); i++) {
                    if (i == 0) {
                        subQuery.append("ID = ? ");
                    } else {
                        subQuery.append("OR ID = ? ");
                    }
                    subQuery.append(authors.get(i).getId());
                }
//                String query = "SELECT * FROM Books WHERE Title = ? OFFSET " + offsetRows + " FETCH NEXT 10 ROWS ONLY";
                String query = "SELECT * FROM Books WHERE " + subQuery;
//                int offsetRows = Integer.parseInt(offset) * 10;
                preparedStatement = getConnection().prepareStatement(query);
                for (int i = 0; i < authors.size(); i++) {
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
