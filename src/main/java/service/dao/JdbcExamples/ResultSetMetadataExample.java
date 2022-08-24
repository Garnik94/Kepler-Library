package service.dao.JdbcExamples;

import com.sun.rowset.FilteredRowSetImpl;

import javax.servlet.ServletContext;
import java.sql.*;

public class ResultSetMetadataExample {

    public Connection connection;

    public ResultSetMetaData resultSetMetaData;

    public ResultSetMetadataExample(ServletContext servletContext) {
        connection = (Connection) servletContext.getAttribute("dbConnection");
        String query = "SELECT * FROM Books";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            resultSetMetaData = resultSet.getMetaData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getColumnCount() {
        try {
            return "getColumnCount() - "+ resultSetMetaData.getColumnCount();
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    public String getColumnName(int index) {
        try {
            return "getColumnName() - "+ resultSetMetaData.getColumnName(index);
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

    public String getColumnType(int index) {
        try {
            return "getColumnType() - "+ resultSetMetaData.getColumnType(index);
        } catch (SQLException throwables) {
            throw new RuntimeException();
        }
    }

}
