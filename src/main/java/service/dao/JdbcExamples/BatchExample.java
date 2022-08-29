package service.dao.JdbcExamples;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchExample {

    public Connection connection;

    public BatchExample(ServletContext servletContext) {
        connection = (Connection) servletContext.getAttribute("dbConnection");
    }

    public void batchWithStatement() {
        String query1 = "INSERT INTO JdbcExamples VALUES ('First batch')";
        String query2 = "INSERT INTO JdbcExamples VALUES ('Second batch')";
        String query3 = "INSERT INTO JdbcExamples VALUES ('Third batch')";
        String query4 = "INSERT INTO JdbcExamples VALUES ('Fourth batch')";
        try {
            Statement statement = connection.createStatement();
            statement.addBatch(query1);
            statement.addBatch(query2);
            statement.addBatch(query3);
            statement.addBatch(query4);

            statement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void batchWithPreparedStatement() {
        String query = "INSERT INTO JdbcExamples VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            preparedStatement.setString(1, "First batch");
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Second batch");
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Third batch");
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Fourth batch");
            preparedStatement.addBatch();

            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
