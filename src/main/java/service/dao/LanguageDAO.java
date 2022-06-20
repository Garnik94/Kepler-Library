package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAO {

//    static {
//        setLanguages(getAllLanguages());
//    }

    private static List<Language> languages;

    public static List<Language> getLanguages() {
        return new ArrayList<>(languages);
    }

    public static void setLanguages(List<Language> languages) {
        LanguageDAO.languages = languages;
    }

//    public static Connection getConnection() throws SQLException {
//        DriverManager.registerDriver(new SQLServerDriver());
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
//                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
//        return DriverManager.getConnection(url);
//    }

    public static List<Language> getAllLanguages(Connection connection) {
        List<Language> languages = new ArrayList<>();
        try {
            String query = "SELECT * FROM Languages";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Language language = new Language(resultSet.getString("Language_Name"));
                language.setId(resultSet.getInt("Language_Id"));
                languages.add(language);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return languages;
    }

    public static Language getLanguageById(int id) {
            for (Language currentLanguage : languages) {
                if (currentLanguage.getId() == id) {
                    return currentLanguage;
                }
            }
//        try {
//            String query = "SELECT * FROM Languages WHERE ID = ?";
//            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
//            preparedStatement.setString(1, Integer.toString(id));
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return new Language(resultSet.getString("Language"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public static int getLanguageIdByName(Language language) {
        for (Language currentLanguage : languages) {
            if (currentLanguage.getLanguage().equals(language.getLanguage())) {
                return currentLanguage.getId();
            }
        }
//        try {
//            String query = "SELECT * FROM Languages WHERE Language = ?";
//            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
//            preparedStatement.setString(1, language.getLanguage());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getInt("ID");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return -1;
    }

    public static int addNewLanguage(Connection connection, Language language) {
        int languageId = getLanguageIdByName(language);
        if (languageId != -1) {
            return languageId;
        }
        try {
            String query = "INSERT INTO Languages VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, language.getLanguage());
            preparedStatement.executeUpdate();
            setLanguages(getAllLanguages(connection));
            languageId = getLanguageIdByName(language);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return languageId;
    }

}
