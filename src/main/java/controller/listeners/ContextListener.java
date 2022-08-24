package controller.listeners;

import com.google.common.base.Optional;
import service.dao.DbConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public void contextInitialized(ServletContextEvent sce) {
        String url = sce.getServletContext().getInitParameter("dbConnectionUrl");
        try {
            Connection connection = DbConnectionManager.getConnection(url);
            sce.getServletContext().setAttribute("dbConnection", Optional.of(connection).get());
            setCategories(getAllCategories(connection));
            setDocumentTypes(getAllDocumentTypes(connection));
            setLanguages(getAllLanguages(connection));
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
