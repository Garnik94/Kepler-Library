package service.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class ShowDatabaseMetaData {

    private DatabaseMetaData databaseMetaData;

    public ShowDatabaseMetaData(Connection connection) {
        try {
            this.databaseMetaData = connection.getMetaData();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDriverName(){
        try {
            return "getDriverName() - " + databaseMetaData.getDriverName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDriverVersion(){
        try {
            return "getDriverVersion() - " + databaseMetaData.getDriverVersion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUserName(){
        try {
            return "getUserName() - " + databaseMetaData.getUserName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDatabaseProductName(){
        try {
            return "getDatabaseProductName() - " + databaseMetaData.getDatabaseProductName();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDatabaseProductVersion(){
        try {
            return "getDriverName() - " + databaseMetaData.getDatabaseProductVersion();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
