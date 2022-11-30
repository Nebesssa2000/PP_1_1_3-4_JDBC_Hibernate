package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import static jm.task.core.jdbc.util.Util.getConnection;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        final String testName = "Ivan";
        final String testLastName = "Ivanov";
        final byte testAge = 5;

        UserService userService = new UserServiceImpl();
        UserDao userDao = new UserDaoJDBCImpl();
            userService.createUsersTable();
            userService.saveUser(testName,testLastName,testAge);
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
