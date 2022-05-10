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
            while (resultSet.next()) {
                return new Language(resultSet.getString("Language"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
