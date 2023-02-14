package org.example.dao;

import org.example.entity.Abonent;
import org.example.exceptions.DaoException;
import org.example.utils.DataBaseConnection;
import org.example.utils.DatabaseRead;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbonentDaoImpl implements AbonentDao {
    private final static AbonentDaoImpl INSTANCE = new AbonentDaoImpl();

    @Override
    public List<Abonent> findAll() throws DaoException {
        Connection connection = DataBaseConnection.getConnection();
        List<Abonent> abonents = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM abonents");
            while (rs.next()) {
                abonents.add(buildAbonent(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return abonents;
    }

    @Override
    public Abonent findById(Integer id) throws DaoException {
        ResultSet rs = DatabaseRead.getResultSet(String.format("SELECT * FROM abonents WHERE id=%d", id));
        try {
            return buildAbonent(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Abonent save(Abonent entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public void update(Abonent entity) throws DaoException {

    }

    @Override
    public Optional<Abonent> findByName(String name, String surname) throws DaoException {
        return Optional.empty();
    }

    public static AbonentDaoImpl getInstance() {
        return INSTANCE;
    }

    private Abonent buildAbonent(ResultSet rs) throws SQLException {
        return new Abonent(rs.getInt("id"), rs.getString("name"
        ), rs.getString("surname"), rs.getString("phone"));
    }


}
