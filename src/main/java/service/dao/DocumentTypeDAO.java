package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.DocumentType;
import model.content.Journal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentTypeDAO {

    static {
        setDocumentTypes(getAllDocumentTypes());
    }

    private static List<DocumentType> documentTypes = new ArrayList<>();

    public static List<DocumentType> getDocumentTypes() {
        return new ArrayList<>(documentTypes);
    }

    public static void setDocumentTypes(List<DocumentType> documentTypes) {
        DocumentTypeDAO.documentTypes = documentTypes;
    }

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static List<DocumentType> getAllDocumentTypes() {
        List<DocumentType> documentTypes = new ArrayList<>();
        try {
            String query = "SELECT * FROM Document_Types";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DocumentType documentType = new DocumentType(resultSet.getString("Document_Type_Name"));
                documentType.setId(resultSet.getInt("Document_Type_Id"));
                documentTypes.add(documentType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentTypes;
    }

    public static DocumentType getDocumentTypeById(int id) {
        for (DocumentType currentDocumentType : documentTypes) {
            if (currentDocumentType.getId() == id) {
                return currentDocumentType;
            }
        }
//        try {
//            String query = "SELECT * FROM Document_Types WHERE ID = ?";
//            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
//            preparedStatement.setString(1, Integer.toString(id));
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return new DocumentType(resultSet.getString("Type"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public static int getDocumentTypeIdByName(DocumentType documentType) {
        for (DocumentType currentDocumentType : documentTypes) {
            if (currentDocumentType.getType().equals(documentType.getType())) {
                return currentDocumentType.getId();
            }
        }
//        try {
//            String query = "SELECT * FROM Document_Types WHERE Type = ?";
//            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
//            preparedStatement.setString(1, documentType.getType());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getInt("ID");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return -1;
    }

    public static int addNewDocumentType(DocumentType documentType) {
        int documentTypeId = getDocumentTypeIdByName(documentType);
        if (documentTypeId != -1) {
            return documentTypeId;
        }
        try {
            String query = "INSERT INTO Document_Types VALUES (?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, documentType.getType());
            preparedStatement.executeUpdate();
            setDocumentTypes(getAllDocumentTypes());
            documentTypeId = getDocumentTypeIdByName(documentType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentTypeId;
    }
}
