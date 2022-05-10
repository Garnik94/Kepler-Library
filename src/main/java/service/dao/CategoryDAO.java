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
            while (resultSet.next()) {
                return new Category(resultSet.getString("Category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
