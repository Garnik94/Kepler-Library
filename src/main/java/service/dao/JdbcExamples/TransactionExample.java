package service.dao.JdbcExamples;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class TransactionExample {

    public Connection connection;

    public TransactionExample(ServletContext servletContext) {
        connection = (Connection) servletContext.getAttribute("dbConnection");
    }

    public void transactionFailed() {
        try {
            connection.setAutoCommit(false);
            String query1 = "INSERT INTO JdbcExamples VALUES ('First transaction')";
            String query2 = "INSERT INTO JdbcExamples VALUES ('Second transaction')";
            String query3 = "INSERT INTO JdbcExamples VALUES ('third transaction'..............)";
            String query4 = "INSERT INTO JdbcExamples VALUES ('Fourth transaction')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
//            Savepoint savepoint = connection.setSavepoint();
            statement.executeUpdate(query3);
            statement.executeUpdate(query4);
            connection.commit();
//            connection.releaseSavepoint(savepoint);
        } catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void transactionSucceeded() {
        try {
            connection.setAutoCommit(false);
            String query1 = "INSERT INTO JdbcExamples VALUES ('First transaction')";
            String query2 = "INSERT INTO JdbcExamples VALUES ('Second transaction')";
            String query3 = "INSERT INTO JdbcExamples VALUES ('third transaction')";
            String query4 = "INSERT INTO JdbcExamples VALUES ('Fourth transaction')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query1);
            statement.executeUpdate(query2);
            statement.executeUpdate(query3);
            statement.executeUpdate(query4);
            connection.commit();
        } catch (SQLException e){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

}
