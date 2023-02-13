package org.example;

import org.example.entity.Abonent;
import org.example.entity.Location;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        printQuery("SELECT * FROM abonents;");
        printQuery("SELECT * FROM locations;");
        List<Abonent> abonents = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        try {
            abonents = getAbonets();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(abonents);
        try {
            locations = getLocations();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(locations);
    }

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

    public static ResultSet readFromDB(String query) {
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

    public static void printQuery(String query) {
        try {
            getInfoByQuery(readFromDB(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List getAbonets() throws SQLException {
        List<Abonent> result = new ArrayList<>();
        String sql = "SELECT * FROM abonents";
        ResultSet rs = readFromDB(sql);

        do {
            int id = 0;
            try {
                id = rs.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String name = null;
            try {
                name = rs.getString(2);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String surname = null;
            try {
                surname = rs.getString(3);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            String phone = null;
            try {
                phone = rs.getString(4);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            result.add(new Abonent(id, name, surname, phone));
        } while (rs.next());
        return result;
    }

    public static List getLocations() throws SQLException {
        List<Location> locations = new ArrayList<>();
        String sql = "SELECT * FROM locations";
        ResultSet rs = readFromDB(sql);
        int id;
        String country;
        String city;
        String address;
        do {
            id = rs.getInt(1);
            country = rs.getString(2);
            city = rs.getString(3);
            address = rs.getString(4);
            locations.add(new Location(id, country, city, address));
        }
        while (rs.next());
        return locations;
    }
    public static void insertAbonent(String base,Abonent abonent){
        String sql=String.format("INSERT INTO %s ",base);
    }
}
