package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.DocumentType;
import model.content.Journal;

import java.sql.*;

public class JournalDAO {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static Journal getJournalById(int id) {
        try {
            String query = "SELECT * FROM Journals WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, Integer.toString(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Journal(resultSet.getString("Journal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
