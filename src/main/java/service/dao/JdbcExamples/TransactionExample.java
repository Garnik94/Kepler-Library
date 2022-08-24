package service.dao.JdbcExamples;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionExample {

    public Connection connection;

    public TransactionExample(ServletContext servletContext) {
        connection = (Connection) servletContext.getAttribute("dbConnection");
    }

    public void transactionFailed() {
        try {
            connection.setAutoCommit(false);
            String query1 = "INSERT INTO Languages VALUES ('Arabic')";
            String query2 = "INSERT INTO Languages VALUES ('Georgian')";
            String query3 = "INSERT INTO Languages VALUES ('French'..............)";
            String query4 = "INSERT INTO Languages VALUES ('German')";
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

    public void transactionSucceeded() {
        try {
            connection.setAutoCommit(false);
            String query1 = "INSERT INTO Languages VALUES ('Arabic')";
            String query2 = "INSERT INTO Languages VALUES ('Georgian')";
            String query3 = "INSERT INTO Languages VALUES ('French')";
            String query4 = "INSERT INTO Languages VALUES ('German')";
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
