package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        final String sql = "CREATE TABLE if not exists User\n" +
                "(id BIGINT NOT NULL AUTO_INCREMENT,\n" +
                " name VARCHAR(64) NOT NULL,\n" +
                " lastName VARCHAR(64) NOT NULL,\n" +
                " age TINYINT NOT NULL,\n" +
                " PRIMARY KEY (id));";
        try(PreparedStatement prsmt = connection.prepareStatement(connection.nativeSQL(sql))) {
            prsmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        final String sql = "DROP TABLE if exists User";
        try(PreparedStatement prsmt = connection.prepareStatement(sql)) {
            prsmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        final String sql = "INSERT INTO User (name, lastName, age) VALUES(?, ?, ?)";
        try(PreparedStatement prsmt = connection.prepareStatement(sql)) {
            prsmt.setString(1, name);
            prsmt.setString(2, lastName);
            prsmt.setByte(3, age);
            prsmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        final String sql = "DELETE FROM User WHERE id IN (1)";
        try(PreparedStatement prsmt = getConnection().prepareStatement(sql)) {
            prsmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> Userlist = new ArrayList<>();
        final String sql = "SElECT * FROM User;";
        try(PreparedStatement prsmt = getConnection().prepareStatement(sql)) {
            ResultSet rs = prsmt.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                Userlist.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Userlist;
    }

    public void cleanUsersTable() {
        final String sql = "TRUNCATE TABLE User;";
        try(PreparedStatement prsmt = getConnection().prepareStatement(sql)) {
            prsmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
