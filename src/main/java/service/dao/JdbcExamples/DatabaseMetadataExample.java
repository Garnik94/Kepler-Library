package service.dao.JdbcExamples;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DatabaseMetadataExample {

    public Connection connection;

    public DatabaseMetaData databaseMetaData;

    public DatabaseMetadataExample(ServletContext servletContext) {
        connection = (Connection) servletContext.getAttribute("dbConnection");
        try {
            databaseMetaData = connection.getMetaData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getDriverName() {
        try {
            return "getDriverName() - "+ databaseMetaData.getDriverName();
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    public String getDriverVersion() {
        try {
            return "getDriverVersion() - "+ databaseMetaData.getDriverVersion();
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    public String getUserName() {
        try {
            return "getUserName() - "+ databaseMetaData.getUserName();
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    public String getDatabaseProductName() {
        try {
            return "getDatabaseProductName() - "+ databaseMetaData.getDatabaseProductName();
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    public String getDatabaseProductVersion() {
        try {
            return "getDatabaseProductVersion() - "+ databaseMetaData.getDatabaseProductVersion();
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

}
