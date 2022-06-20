package service.dao;

import exceptions.AbsentUserException;
import model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

//    public static Connection getConnection() throws SQLException {
//        DriverManager.registerDriver(new SQLServerDriver());
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
//                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
//        return DriverManager.getConnection(url);
//    }

    public static User getUser(Connection connection, User user) throws AbsentUserException {
        try {
            String query = "SELECT * FROM Users WHERE Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User foundUser = new User(resultSet.getString("Username"),
                        resultSet.getString("Password"),
                        resultSet.getString("Email"));
                foundUser.setId(resultSet.getInt("ID"));
                foundUser.setHasEditPermission(resultSet.getInt("Has_Edit_Permission"));
                return foundUser;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new AbsentUserException();
    }

    public static boolean addNewUser(Connection connection,
                                     String username,
                                     String password,
                                     String email) {
        try {
            getUser(connection, new User(username));
            return false;
        } catch (AbsentUserException e) {
            try {
                String passwordConvertedToMD5 = md5Converter(password);
                String query = "INSERT INTO Users VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, passwordConvertedToMD5);
                preparedStatement.setString(3, email);
                preparedStatement.setInt(4, 0);
                preparedStatement.executeUpdate();
                return true;
            } catch (SQLException e1) {
                return false;
            }
        }
    }

    public static void deleteUser(Connection connection, User user) {
        try {
            String query = "DELETE FROM Users WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void permitUser(Connection connection, User user) {
        try {
            String query = "UPDATE Users SET Has_Edit_Permission = 1 WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void forbidUser(Connection connection, User user) {
        try {
            String query = "UPDATE Users SET Has_Edit_Permission = 0 WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String md5Converter(String password) {
        StringBuilder convertPassword = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] array = messageDigest.digest(password.getBytes());
            for (byte b : array
            ) {
                convertPassword.append(b);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new String(convertPassword);
    }

}
