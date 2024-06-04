package org.example.module01.lesson48;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleProject {
    private static final Connection conn = null;
    private static final String CREATE_TABLE = "CREATE TABLE Users\n" +
            "(\n" +
            "    id              serial primary key,\n" +
            "    name            varchar(20),\n" +
            "    email           varchar(20),\n" +
            "    phoneNumber     varchar(20)\n" +
            ");";

    private static final String INSERT_INTO = "INSERT INTO Users(name, email, phoneNumber) VALUES(?, ?, ?);";

    private static final String DELETE_USER = "DELETE FROM Users WHERE id = ?;";
    private static final String SELECT_ALL_USERS = "SELECT * FROM Users;";

    public static void main(String[] args) {
//        createTable();
//        insertUsers();
//        deleteUser(1);
        selectUsers().forEach(System.out::println);
        closeConnection();
    }

    private static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createTable() {
        try (final Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "mysecretpassword")) {
            PreparedStatement preparedStatement = conn.prepareStatement(CREATE_TABLE);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<Users> selectUsers() {
        try (final Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "mysecretpassword")) {
            PreparedStatement preparedStatement = conn.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Users> users = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phoneNumber");
                Users user = new Users(id, name, email, phoneNumber);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void insertUsers() {
        try {
            final Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "mysecretpassword");
            PreparedStatement preparedStatement = conn.prepareStatement(INSERT_INTO);
            preparedStatement.setString(1, "John");
            preparedStatement.setString(2, "john123@gmail.com");
            preparedStatement.setString(3, "121-343-565");
            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void deleteUser(int userId) {
        try (
                final Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "mysecretpassword");
                PreparedStatement preparedStatement = conn.prepareStatement(DELETE_USER)) {

            preparedStatement.setInt(1, userId);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
