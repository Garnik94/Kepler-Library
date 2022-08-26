package service.dao;

import com.google.common.base.Objects;
import model.content.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguageDAO {

    private static List<Language> languages;

    private static List<Language> allLanguages;

    public static List<Language> getLanguages() {
        return new ArrayList<>(languages);
    }

    public static void setLanguages(List<Language> languages) {
        LanguageDAO.languages = languages;
    }

    public static void setAllLanguages(List<Language> languages) {
        LanguageDAO.allLanguages = languages;
    }

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
            for (Language currentLanguage : allLanguages) {
                if (currentLanguage.getId() == id) {
                    return currentLanguage;
                }
            }
        return null;
    }

    public static int getLanguageIdByName(Language language) {
        for (Language currentLanguage : allLanguages) {
            if (Objects.equal(currentLanguage.getLanguage(), language.getLanguage())) {
                return currentLanguage.getId();
            }
        }
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
