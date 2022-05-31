package service.dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import model.content.Author;
import model.content.Category;
import model.content.DocumentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    static {
        setCategories(getAllCategories());
    }

    private static List<Category> categories;

    public static List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public static void setCategories(List<Category> categories) {
        CategoryDAO.categories = categories;
    }

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new SQLServerDriver());
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Kepler_Library;" +
                "integratedSecurity=true;encrypt=true;trustServerCertificate=true";
        return DriverManager.getConnection(url);
    }

    public static List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        try {
            String query = "SELECT * FROM Categories";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(resultSet.getString("Category_Name"));
                category.setId(resultSet.getInt("Category_Id"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    public static Category getCategoryById(int id) {
        for (Category currentCategory : categories) {
            if (currentCategory.getId() == id) {
                return currentCategory;
            }
        }
//        try {
//            String query = "SELECT * FROM Categories WHERE ID = ?";
//            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
//            preparedStatement.setString(1, Integer.toString(id));
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return new Category(resultSet.getString("Category"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return null;
    }

    public static int getCategoryIdByName(Category category) {
        for (Category currentCategory : categories) {
            if (currentCategory.getCategoryName().equals(category.getCategoryName())) {
                return currentCategory.getId();
            }
        }
//        try {
//            String query = "SELECT * FROM Categories WHERE Category = ?";
//            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
//            preparedStatement.setString(1, category.getCategoryName());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if (resultSet.next()) {
//                return resultSet.getInt("ID");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        return -1;
    }

    public static int addNewCategory(Category category) {
        int categoryId = getCategoryIdByName(category);
        if (categoryId != -1) {
            return categoryId;
        }
        try {
            String query = "INSERT INTO Categories VALUES (?)";
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.executeUpdate();
            setCategories(getAllCategories());
            categoryId = getCategoryIdByName(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }

}
