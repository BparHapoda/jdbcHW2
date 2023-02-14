package org.example;

import org.example.dao.AbonentDaoImpl;
import org.example.dao.LocationDaoImpl;
import org.example.entity.Abonent;
import org.example.exceptions.DaoException;
import org.example.utils.PrintQuery;




public class Main {
    public static void main(String[] args) {


        AbonentDaoImpl adi=new AbonentDaoImpl();
        try {
            System.out.println(adi.findById(3));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        LocationDaoImpl ldi=new LocationDaoImpl();
        try {
            System.out.println(ldi.findById(2));
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }
        PrintQuery.printQuery("SELECT * FROM abonents");

    }







    public static void insertAbonent(String base,Abonent abonent){
        String sql=String.format("INSERT INTO %s ",base);
    }
}
