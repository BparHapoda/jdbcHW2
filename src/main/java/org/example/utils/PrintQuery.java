package org.example.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintQuery {
    public static void getInfoByQuery(ResultSet rs) throws SQLException {
        printFooter();
        int columns = 0;
        try {
            columns = rs.getMetaData().getColumnCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (int j = 1; j <= columns; j++) {
            if (j == 1) {
                System.out.print("|");
            }
            try {
                System.out.printf("\t\t\t\t%-20s|", rs.getMetaData().getColumnName(j));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("");
        printFooter();
        do {
            for (int i = 1; i <= columns; i++) {
                if (i == 1) {
                    System.out.print("|");
                }
                try {
                    System.out.printf("\t\t\t\t%-20s|", rs.getString(i));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("");
            printFooter();
        }
        while (rs.next());


    }

    public static void printFooter() {
        System.out.println("--------------------------------------------------------------------" +
                "-----------------------------------------------------------------------------");
    }

    public static void printQuery(String query) {
        try {
            getInfoByQuery(DatabaseRead.getResultSet(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
