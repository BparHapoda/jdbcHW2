package org.example.dao;

import org.example.entity.Abonent;
import org.example.entity.Entity;
import org.example.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao <K,T extends Entity>{
    List <T> findAll() throws DaoException;

    T findById (K id) throws DaoException, SQLException;

    void update (T entity) throws DaoException;

    T save (T entity)throws DaoException;

    boolean delete (K id) throws DaoException;


}
