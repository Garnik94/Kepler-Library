package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.Author;
import model.content.Category;

import java.sql.*;

public class CategoryDAO {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static Category getCategoryById(int id) {
        try {
            String query = "SELECT * FROM Categories WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, Integer.toString(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Category(resultSet.getString("Category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getCategoryIdByName(Category category) {
        try {
            String query = "SELECT * FROM Categories WHERE Category = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, category.getCategoryName());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int addNewCategory(Category category) {
        int categoryId = getCategoryIdByName(category);
        if (categoryId != -1) {
            return categoryId;
        }
        try {
            String query = "INSERT INTO Categories VALUES (?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.executeUpdate();
            categoryId = getCategoryIdByName(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }

}
