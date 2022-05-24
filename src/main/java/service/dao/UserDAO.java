package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import exceptions.AbsentUserException;
import model.User;

import java.sql.*;

public class UserDAO {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static User getUser(User user) throws AbsentUserException {
        try {
            String query = "SELECT * FROM Users WHERE Username = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User foundUser = new User(resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"));
                foundUser.setId(resultSet.getInt("ID"));
                return foundUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new AbsentUserException();
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

    public static void deleteUser(User user) {
        try {
            String query = "DELETE FROM Users WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
