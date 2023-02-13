package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    static final String url = "jdbc:postgresql://localhost:5432/phonebook";
    static final String login = "postgres";
    static final String password = "12345";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, login, password);
            if (connection != null) {
                System.out.println("Connection ok");
            } else {
                System.out.println("Failed connection");
            }
        } catch (SQLException e) {
            System.err.format("SQL State : %s\n%s", e.getSQLState(), e.getMessage());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
