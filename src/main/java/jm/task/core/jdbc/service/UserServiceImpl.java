package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserServiceImpl implements UserService {
    private static final long serialVersionUID = 1L;
    User user = new User();
    //private UserDao userDao = new UserDaoJDBCImpl();

    public void createUsersTable() {
        try(Connection connection = getConnection();
            PreparedStatement prsmt = connection.prepareStatement
                    ("CREATE TABLE if not exists Users(id int NOT NULL, name VARCHAR(32) NOT NULL , lastName VARCHAR(32) NOT NULL , age Integer NOT NULL, PRIMARY KEY (id));")) {
            prsmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(Connection connection = getConnection();
            PreparedStatement prsmt = connection.prepareStatement
                    ("DROP TABLE Users")) {
            prsmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = getConnection();
            PreparedStatement prsmt = connection.prepareStatement
                    ("INSERT INTO Users VALUES(1, ?, ?, ?)")) {
            prsmt.setString(1, user.getName());
            prsmt.setString(2,user.getLastName());
            prsmt.setByte(3, user.getAge());
            prsmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try(PreparedStatement prsmt = getConnection().prepareStatement
                ("DELETE FROM Users WHERE id = ?")) {
            prsmt.setLong(1, user.getId());
            prsmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> list = null;
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SElECT * FROM Users;");
            while (resultSet.next()) {
                list = new ArrayList<>();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void cleanUsersTable() {
        try(PreparedStatement prsmt = getConnection().prepareStatement
                ("DELETE FROM Users ")) {
            prsmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
