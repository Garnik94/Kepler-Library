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
        String query1 = "INSERT INTO Languages VALUES ('Arabic')";
        String query2 = "INSERT INTO Languages VALUES ('Georgian')";
        String query3 = "INSERT INTO Languages VALUES ('French')";
        String query4 = "INSERT INTO Languages VALUES ('German')";
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
        String query = "INSERT INTO Languages VALUES (?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Arabic");
            preparedStatement.addBatch();
            preparedStatement.setString(1, "Georgian");
            preparedStatement.addBatch();
            preparedStatement.setString(1, "French");
            preparedStatement.addBatch();
            preparedStatement.setString(1, "German");
            preparedStatement.addBatch();

            preparedStatement.executeBatch();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
