package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.DocumentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocumentTypeDAO {

    private static List<DocumentType> documentTypes;

    public static List<DocumentType> getDocumentTypes() {
        return new ArrayList<>(documentTypes);
    }

    public static void setDocumentTypes(List<DocumentType> documentTypes) {
        DocumentTypeDAO.documentTypes = documentTypes;
    }

    public static List<DocumentType> getAllDocumentTypes(Connection connection) {
        List<DocumentType> documentTypes = new ArrayList<>();
        try {
            String query = "SELECT * FROM Document_Types";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
        return null;
    }

    public static int getDocumentTypeIdByName(DocumentType documentType) {
        for (DocumentType currentDocumentType : documentTypes) {
            if (currentDocumentType.getType().equals(documentType.getType())) {
                return currentDocumentType.getId();
            }
        }
        return -1;
    }

    public static int addNewDocumentType(Connection connection, DocumentType documentType) {
        int documentTypeId = getDocumentTypeIdByName(documentType);
        if (documentTypeId != -1) {
            return documentTypeId;
        }
        try {
            String query = "INSERT INTO Document_Types VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, documentType.getType());
            preparedStatement.executeUpdate();
            setDocumentTypes(getAllDocumentTypes(connection));
            documentTypeId = getDocumentTypeIdByName(documentType);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return documentTypeId;
    }
}
