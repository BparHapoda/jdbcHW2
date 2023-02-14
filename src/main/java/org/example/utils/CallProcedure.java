package org.example.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallProcedure {
    public static void displayMsg(){

        try {
            String sql="{call display_msg(?)}";
            Connection connection= DataBaseConnection.getConnection();
            connection.setAutoCommit(true);
            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.registerOutParameter(1, Types.VARCHAR);
            callableStatement.setString(1,"m");
            callableStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
