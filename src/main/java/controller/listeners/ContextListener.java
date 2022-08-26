package controller.listeners;

import com.google.common.base.Optional;
import service.dao.DbConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

import static service.dao.CategoryDAO.*;
import static service.dao.DocumentTypeDAO.*;
import static service.dao.LanguageDAO.*;

//@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String url = sce.getServletContext().getInitParameter("dbConnectionUrl");
        try {
            Connection connection = DbConnectionManager.getConnection(url);
            sce.getServletContext().setAttribute("dbConnection", connection);

            setCategories(getAllCategories(connection));
            setAllCategories(getAllCategories(connection));
            setDocumentTypes(getAllDocumentTypes(connection));
            setAllDocumentTypes(getAllDocumentTypes(connection));
            setLanguages(getAllLanguages(connection));
            setAllLanguages(getAllLanguages(connection));
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
