package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    static final String url = "jdbc:postgresql://localhost:5432/phonebook";




    public static Connection getConnection() {
        Properties properties=new Properties();
        properties.put("user","postgres");
        properties.put("password","12345");
        properties.put("autoReconnect","true");
        properties.put("charackterEncoding","UTF-8");
        properties.put("useUnicode","true");
        properties.put("escapeSyntaxCallMode","callIfNoReturn");


        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, properties);
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
