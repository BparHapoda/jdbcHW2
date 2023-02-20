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


        AbonentDaoImpl adi=new AbonentDaoImpl();
        LocationDaoImpl ldi=new LocationDaoImpl();
/*        try {
            System.out.println(adi.findAll());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        LocationDaoImpl ldi=new LocationDaoImpl();
        try {
            System.out.println(ldi.findAll());
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
*/
    /*
        AbonentDaoImpl abonentDao=new AbonentDaoImpl();
        try {
            System.out.println(abonentDao.findById(1));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        LocationDaoImpl locationDao=new LocationDaoImpl();
        try {
            System.out.println(locationDao.findById(1));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

        try {
            System.out.println(abonentDao.save(new Abonent("Dima","Boronin","+79219671388")));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        try {
            locationDao.save(new Location("Georgia","Batumi","Sherifa c street 49A"));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
*/

        PrintQuery.printQuery("SELECT * FROM abonents");
      PrintQuery.printQuery("SELECT * FROM locations");
        try {
            AbonentDaoImpl.getInstance().update(Abonent.getAbonent(16,"Hren","Hrenov","123456"));
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
