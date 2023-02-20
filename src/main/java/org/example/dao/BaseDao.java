package org.example.dao;

import org.example.entity.Abonent;
import org.example.entity.Entity;
import org.example.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface BaseDao <K,T extends Entity>{

    List <T> findAll() throws DaoException;

    Optional<T> findById (K id) throws DaoException;

    void update (T entity) throws DaoException;

    T save (T entity)throws DaoException;

    boolean delete (K id) throws DaoException;


}
