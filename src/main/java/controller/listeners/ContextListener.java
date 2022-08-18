package controller.listeners;

import com.google.common.base.Optional;
import service.dao.DbConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

import static service.dao.CategoryDAO.getAllCategories;
import static service.dao.CategoryDAO.setCategories;
import static service.dao.DocumentTypeDAO.getAllDocumentTypes;
import static service.dao.DocumentTypeDAO.setDocumentTypes;
import static service.dao.LanguageDAO.getAllLanguages;
import static service.dao.LanguageDAO.setLanguages;

//@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    @SuppressWarnings("unchecked")
    public void contextInitialized(ServletContextEvent sce) {
        String url = sce.getServletContext().getInitParameter("dbConnectionUrl");
        try {
            Optional<Connection> connection = Optional.of(DbConnectionManager.getConnection(url));
            sce.getServletContext().setAttribute("dbConnection", connection);
            setCategories(getAllCategories(connection.get()));
            setDocumentTypes(getAllDocumentTypes(connection.get()));
            setLanguages(getAllLanguages(connection.get()));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = (Connection) sce.getServletContext().getAttribute("dbConnection");
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
