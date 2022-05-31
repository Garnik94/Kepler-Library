package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.Category;
import model.content.DocumentType;
import model.content.Journal;
import model.content.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JournalDAO {

    static {
        setJournals(getAllJournals());
    }

    private static List<Journal> journals;

    public static List<Journal> getJournals() {
        return new ArrayList<>(journals);
    }

    public static void setJournals(List<Journal> journals) {
        JournalDAO.journals = journals;
    }

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static List<Journal> getAllJournals() {
        List<Journal> journals = new ArrayList<>();
        try {
            String query = "SELECT * FROM Journals";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Journal journal = new Journal(resultSet.getString("Journal_Name"));
                journal.setId(resultSet.getInt("Journal_Id"));
                journals.add(journal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journals;
    }

    public static Journal getJournalById(int id) {
        for (Journal currentJournal : journals) {
            if (currentJournal.getId() == id) {
                return currentJournal;
            }
        }
//        try {
//            String query = "SELECT * FROM Journals WHERE ID = ?";
//            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
//            preparedStatement.setString(1, Integer.toString(id));
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return new Journal(resultSet.getString("Journal"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public static int getJournalIdByName(Journal journal) {
        for (Journal currentJournal : journals) {
            if (currentJournal.getJournal().equals(journal.getJournal())) {
                return currentJournal.getId();
            }
        }
//        try {
//            String query = "SELECT * FROM Journals WHERE Journal = ?";
//            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
//            preparedStatement.setString(1, journal.getJournal());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {return resultSet.getInt("ID");}
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return -1;
    }

    public static int addNewJournal(Journal journal) {
        int journalId = getJournalIdByName(journal);
        if (journalId != -1){
            return journalId;
        }
        try {
            String query = "INSERT INTO Journals VALUES (?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, journal.getJournal());
            preparedStatement.executeUpdate();
            setJournals(getAllJournals());
            journalId =  getJournalIdByName(journal);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return journalId;
    }
}
