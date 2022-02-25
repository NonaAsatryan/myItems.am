package manager;

import db.DBConnectionProvider;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryManager {

    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    public static void add(Category category) {
        String sql = "insert into category(name) VALUES(?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                category.setId(id);
            }
            System.out.println("User was added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getCategoryById(int id) {
        String sql = "SELECT * FROM category WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getCategoryFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Category> getAllICategories () {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM category";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt(1));
                category.setName(resultSet.getString(2));
                categories.add(category);
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return categories;
    }

    private Category getCategoryFromResultSet(ResultSet resultSet) {
        try {
            return Category.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
