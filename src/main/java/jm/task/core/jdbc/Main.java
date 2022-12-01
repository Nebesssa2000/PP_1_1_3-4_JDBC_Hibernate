package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userServiceImpl = new UserServiceImpl();
        userServiceImpl.createUsersTable();
        userServiceImpl.saveUser("Jack", "Black", (byte)30);
        userServiceImpl.saveUser("Joe", "White", (byte)35);
        userServiceImpl.saveUser("Bob", "Ferry", (byte)40);
        userServiceImpl.saveUser("Natan", "Jones", (byte)45);
        userServiceImpl.getAllUsers();
        userServiceImpl.cleanUsersTable();
        userServiceImpl.dropUsersTable();
    }
}
