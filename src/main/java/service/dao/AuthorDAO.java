package service.dao;

import model.content.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    public static Author getAuthorById(Connection connection, int id) {
        try {
            String query = "SELECT * FROM Authors WHERE Author_Id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Integer.toString(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Author(resultSet.getString("Author_Name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getAuthorIdByName(Connection connection, Author author) {
        try {
            String query = "SELECT * FROM Authors WHERE Author_Name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, author.getAuthorName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("Author_Id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static List<Author> getAuthorsByCoincidence(Connection connection, String searchingArg) {
        List<Author> authors = new ArrayList<>();
        try {
            String query = "SELECT * FROM Authors WHERE Author_Name LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchingArg + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Author author = new Author(resultSet.getString("Author_Name"));
                author.setId(resultSet.getInt("Author_Id"));
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public static int addNewAuthor(Connection connection, Author author) {
        int authorId = getAuthorIdByName(connection, author);
        if (authorId != -1) {
            return authorId;
        }
        try {
            String query = "INSERT INTO Authors VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, author.getAuthorName());
            preparedStatement.executeUpdate();
            authorId = getAuthorIdByName(connection, author);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorId;
    }

}
