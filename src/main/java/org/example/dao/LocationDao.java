package org.example.dao;

import org.example.entity.Abonent;
import org.example.entity.Location;
import org.example.exceptions.DaoException;

import java.util.List;

public interface LocationDao extends BaseDao<Integer, Location> {
    @Override
    List<Location> findAll() throws DaoException;

    @Override
    Location findById(Integer id) throws DaoException;

    @Override
    void update(Location entity) throws DaoException;

    @Override
    Location save(Location entity) throws DaoException;

    @Override
    boolean delete(Integer id) throws DaoException;
}
