package manager;

import db.DBConnectionProvider;
import model.ItemImage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemImageManager {

    private  static Connection connection = DBConnectionProvider.getInstance().getConnection();
    private ItemManager itemManager=new ItemManager();

    public void add(ItemImage itemImages) {
        String sql = "insert into itemImages(image_url,item_id) VALUES(?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, itemImages.getImageUrl());
            statement.setInt(2,itemImages.getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                itemImages.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private ItemImage getItemImageFromResultSet(ResultSet resultSet) {
        try {
            return ItemImage.builder()
                    .id(resultSet.getInt(1))
                    .imageUrl(resultSet.getString(2))
                    .item(itemManager.getItemById(resultSet.getInt(3)))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<ItemImage> getAllItemImagesByItemId (int id) {
        List<ItemImage> itemImages = new ArrayList<>();
        String sql = "SELECT * FROM itemImage WHERE item_id = " + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                ItemImage itemImage = new ItemImage();
                itemImage.setId(resultSet.getInt(1));
                itemImage.setImageUrl(resultSet.getString(2));
                itemImage.setItem(itemManager.getItemById(resultSet.getInt(3)));
                itemImages.add(itemImage);
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        }
        return itemImages;
    }

    public void deleteItemImageById(int id) {
        String sql = "delete FROM itemImage WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
