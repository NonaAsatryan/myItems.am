package manager;

import db.DBConnectionProvider;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private CategoryManager userManager = new CategoryManager();
    private UserManager categoryManager = new UserManager();


    public void add(Item item) {
        String sql = "insert into item(title,price,category_id,user_id,picture_url) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, item.getTitle());
            statement.setDouble(2, item.getPrice());
            statement.setInt(3, item.getCategoryId());
            statement.setInt(4, item.getUserId());
            statement.setString(5, item.getPictureUrl());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                item.setId(id);
            }
            System.out.println("User was added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setTitle(resultSet.getString(2));
                item.setPrice(resultSet.getDouble(3));
                item.setCategoryId(resultSet.getInt(4));
                item.setUserId(resultSet.getInt(5));
                item.setPictureUrl(resultSet.getString(6));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getAllItemsByLimit() {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item ORDER BY  DESC LIMIT 20";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt(1));
                item.setTitle(resultSet.getString(2));
                item.setPrice(resultSet.getDouble(3));
                item.setCategoryId(resultSet.getInt(4));
                item.setUserId(resultSet.getInt(5));
                item.setPictureUrl(resultSet.getString(6));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getAllItemsByUserId(int userId) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item where user_id =" + userId;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getAllItemsByUserIdLimit(int userId) {
        List<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM item ORDER BY id = ? DESC LIMIT 20" + userId;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(getItemFromResulSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    public void deleteById(int id) {
        String sql = "delete FROM item WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Item getItemFromResulSet(ResultSet resultSet) {
        try {
            return Item.builder()
                    .id(resultSet.getInt(1))
                    .title(resultSet.getString(2))
                    .price(resultSet.getDouble(3))
                    .categoryId(resultSet.getInt(4))
                    .userId(resultSet.getInt(5))
                    .pictureUrl(resultSet.getString(6))
                    .build();
        } catch (SQLException e) {
            return null;
        }
    }
}
