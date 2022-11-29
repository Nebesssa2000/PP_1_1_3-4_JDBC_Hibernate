package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserServiceImpl implements UserService {
    private static final long serialVersionUID = 1L;
    private UserDao userDao = new UserDaoJDBCImpl();;

    Connection connection =getConnection();


    public void createUsersTable() {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE Users(id int NOT NULL, name VARCHAR(32) NOT NULL ,\n" +
                    "lastName VARCHAR(32) NOT NULL , age Integer NOT NULL, PRIMARY KEY (id));");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE Users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO Users (id int = user.getId) VALUES (name varchar, lastName varchar, age int)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
