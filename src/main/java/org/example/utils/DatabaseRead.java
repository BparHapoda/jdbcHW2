package org.example.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRead {

    public static ResultSet getResultSet(String query) {
        Connection connection = DataBaseConnection.getConnection();
        ResultSet rs;
        Statement statement = null;
        try {
            statement = connection.createStatement();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            try {
                rs = statement.executeQuery(query);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                rs.next();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return rs;
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
