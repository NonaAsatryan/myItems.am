package manager;

import db.DBConnectionProvider;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public void add(User user) {
        String sql = "insert into user(name,surname,email,password) VALUES(?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                user.setId(id);
            }
            System.out.println("User was added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User getByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM user WHERE `email` = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setSurname(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserById(int id) {
        String sql = "SELECT * FROM user WHERE id=" + id;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return getUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUserById(int id) {
        String sql = "delete FROM user WHERE id=" + id;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) {
        try {
            return User.builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString(2))
                    .surname(resultSet.getString(3))
                    .email(resultSet.getString(4))
                    .password(resultSet.getString(5))
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
