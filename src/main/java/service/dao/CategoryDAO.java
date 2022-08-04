package service.dao;

import model.content.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {

    private static List<Category> categories;

    public static List<Category> getCategories() {
        return new ArrayList<>(categories);
    }

    public static void setCategories(List<Category> categories) {
        CategoryDAO.categories = categories;
    }

    public static List<Category> getAllCategories(Connection connection) {
        List<Category> categories = new ArrayList<>();
        try {
            String query = "SELECT * FROM Categories";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
        return null;
    }

    public static int getCategoryIdByName(Category category) {
        for (Category currentCategory : categories) {
            if (currentCategory.getCategoryName().equals(category.getCategoryName())) {
                return currentCategory.getId();
            }
        }
        return -1;
    }

    public static int addNewCategory(Connection connection, Category category) {
        int categoryId = getCategoryIdByName(category);
        if (categoryId != -1) {
            return categoryId;
        }
        try {
            String query = "INSERT INTO Categories VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.executeUpdate();
            setCategories(getAllCategories(connection));
            categoryId = getCategoryIdByName(category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }

}
