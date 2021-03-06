package servlet.listeners;

import service.dao.DbConnectionManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import java.sql.Connection;
import java.sql.SQLException;

import static service.dao.CategoryDAO.getAllCategories;
import static service.dao.CategoryDAO.setCategories;
import static service.dao.DocumentTypeDAO.getAllDocumentTypes;
import static service.dao.DocumentTypeDAO.setDocumentTypes;
import static service.dao.LanguageDAO.getAllLanguages;
import static service.dao.LanguageDAO.setLanguages;

public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String url = sce.getServletContext().getInitParameter("dbConnectionUrl");
        try {
            Connection connection = DbConnectionManager.getConnection(url);
            sce.getServletContext().setAttribute("dbConnection", connection);
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
