package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.User;

import java.sql.*;

public class UserDAO {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static User getUser(String username) {
        try {
            String query = "SELECT * FROM Users WHERE Username = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new User(resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"));
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
