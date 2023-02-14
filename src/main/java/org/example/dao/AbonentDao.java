package org.example.dao;

import org.example.entity.Abonent;
import org.example.exceptions.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface AbonentDao extends BaseDao<Integer, Abonent> {
    @Override
    List<Abonent> findAll() throws DaoException;

    @Override
    Abonent findById(Integer id) throws DaoException, SQLException;

    @Override
    Abonent save(Abonent entity) throws DaoException;

    @Override
    boolean delete(Integer id) throws DaoException;

    @Override
    void update(Abonent entity) throws DaoException;

    Optional<Abonent> findByName(String name, String surname) throws DaoException;
}


