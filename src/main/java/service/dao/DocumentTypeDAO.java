package service.dao;

import com.google.common.base.Objects;
import model.content.DocumentType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentTypeDAO {

    private static List<DocumentType> documentTypes;

    private static List<DocumentType> allDocumentTypes;

    public static List<DocumentType> getDocumentTypes() {
        return new ArrayList<>(documentTypes);
    }

    public static void setDocumentTypes(List<DocumentType> documentTypes) {
        DocumentTypeDAO.documentTypes = documentTypes;
    }

    public static void setAllDocumentTypes(List<DocumentType> documentTypes) {
        DocumentTypeDAO.allDocumentTypes = documentTypes;
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
        for (DocumentType currentDocumentType : allDocumentTypes) {
            if (currentDocumentType.getId() == id) {
                return currentDocumentType;
            }
        }
        return null;
    }

    public static int getDocumentTypeIdByName(DocumentType documentType) {
        for (DocumentType currentDocumentType : allDocumentTypes) {
            if (Objects.equal(currentDocumentType.getType(), documentType.getType())) {
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
