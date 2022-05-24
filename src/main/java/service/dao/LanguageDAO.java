package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.Journal;
import model.content.Language;

import java.sql.*;

public class LanguageDAO {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static Language getLanguageById(int id) {
        try {
            String query = "SELECT * FROM Languages WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, Integer.toString(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Language(resultSet.getString("Language"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getLanguageIdByName(Language language) {
        try {
            String query = "SELECT * FROM Languages WHERE Language = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, language.getLanguage());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int addNewLanguage(Language language) {
        int categoryId = getLanguageIdByName(language);
        if (categoryId != -1) {
            return categoryId;
        }
        try {
            String query = "INSERT INTO Languages VALUES (?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, language.getLanguage());
            preparedStatement.executeUpdate();
            categoryId = getLanguageIdByName(language);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }

}
