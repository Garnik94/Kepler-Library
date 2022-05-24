package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.*;

import java.sql.*;

public class AuthorDAO {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static Author getAuthorById(int id) {
        try {
            String query = "SELECT * FROM Authors WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, Integer.toString(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Author(resultSet.getString("Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getAuthorIdByName(Author author) {
        try {
            String query = "SELECT * FROM Authors WHERE name = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, author.getAuthorName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int addNewAuthor(Author author) {
        int authorId = getAuthorIdByName(author);
        if (authorId != -1){
            return authorId;
        }
        try {
            String query = "INSERT INTO Authors VALUES (?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, author.getAuthorName());
            preparedStatement.executeUpdate();
            authorId = getAuthorIdByName(author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorId;
    }

}
