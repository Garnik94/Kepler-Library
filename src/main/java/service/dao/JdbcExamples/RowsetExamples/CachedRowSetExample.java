package service.dao.JdbcExamples.RowsetExamples;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CachedRowSetExample {

    public void main(ServletContext servletContext) {
        ResultSet resultSet = getResultSet(servletContext);
        try {
            if (resultSet.next()){
                resultSet.getString("Title");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getResultSet(ServletContext servletContext) {
        try {
            Connection connection = (Connection) servletContext.getAttribute("dbConnection");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Books");
            return resultSet;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
