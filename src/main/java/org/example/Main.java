package org.example;

import org.example.entity.Abonent;
import org.example.entity.Location;
import org.example.services.AbonentDaoImpl;
import org.example.services.LocationDaoImpl;
import org.example.exceptions.DaoException;
import org.example.utils.PrintQuery;

import java.util.UUID;


public class Main {
    public static void main(String[] args) {



        PrintQuery.printQuery("SELECT * FROM abonents");
      PrintQuery.printQuery("SELECT * FROM locations");
        try {
            AbonentDaoImpl.getInstance().update(Abonent.getAbonent(16,"Alexey","Navalny","123456"));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        try {
            LocationDaoImpl.getInstance().update(Location.getLocation(3,"Armenia","Erevan","Fifth Avenue"));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        PrintQuery.printQuery("SELECT * FROM abonents");
        PrintQuery.printQuery("SELECT * FROM locations");

        try {
            System.out.println(AbonentDaoImpl.getInstance().findByName("Dima","Boronin").get());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }
}
