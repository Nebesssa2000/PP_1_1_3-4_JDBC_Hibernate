package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static jm.task.core.jdbc.util.Util.getConnection;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final String testName = "Ivan";
        final String testLastName = "Ivanov";
        final byte testAge = 5;

        UserDao userDao = new UserDaoJDBCImpl();
            userDao.createUsersTable();

            userDao.saveUser(testName,testLastName,testAge);
            System.out.println(userDao.getAllUsers());
            userDao.dropUsersTable();

//        try (Connection connection = getConnection();
//             Statement statement = connection.createStatement()){
//            userDao.createUsersTable();
//            userDao.dropUsersTable();
//            userDao.saveUser();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    }
}
