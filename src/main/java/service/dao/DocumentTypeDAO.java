package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.Category;
import model.content.DocumentType;

import java.sql.*;

public class DocumentTypeDAO {

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static DocumentType getDocumentTypeById(int id) {
        try {
            String query = "SELECT * FROM Document_Types WHERE ID = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, Integer.toString(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new DocumentType(resultSet.getString("Type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getDocumentTypeIdByName(DocumentType documentType) {
        try {
            String query = "SELECT * FROM Document_Types WHERE Type = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, documentType.getType());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("ID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static int addNewDocumentType(DocumentType documentType) {
        int categoryId = getDocumentTypeIdByName(documentType);
        if (categoryId != -1) {
            return categoryId;
        }
        try {
            String query = "INSERT INTO Document_Types VALUES (?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, documentType.getType());
            preparedStatement.executeUpdate();
            categoryId = getDocumentTypeIdByName(documentType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }

}
