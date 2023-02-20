package org.example.services;

import org.example.dao.AbonentDao;
import org.example.entity.Abonent;
import org.example.exceptions.DaoException;
import org.example.utils.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Types.NULL;

public class AbonentDaoImpl implements AbonentDao {
    private final static AbonentDaoImpl INSTANCE = new AbonentDaoImpl();

    @Override
    public List<Abonent> findAll() throws DaoException {
        List<Abonent> abonents = new ArrayList<>();
        try (
                Connection connection = DataBaseConnection.getConnection()
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,surname,phone FROM abonents", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                abonents.add(buildAbonent(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return abonents;
    }

    @Override
    public Optional<Abonent> findById(Integer id) throws DaoException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id,name,surname,phone FROM abonents WHERE id=?", Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Abonent abonent = null;
            while (resultSet.next()) {
                abonent = buildAbonent(resultSet);
            }
            return Optional.ofNullable(abonent);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Abonent save(Abonent abonent) throws DaoException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO abonents (name,surname,phone) " +
                            "VALUES ((?),(?),(?));", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, abonent.getName());
            preparedStatement.setString(2, abonent.getSurname());
            preparedStatement.setString(3, abonent.getPhone());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                abonent.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return abonent;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {

        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM abonents WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void update(Abonent abonent) throws DaoException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE abonents SET name=?,surname=?,phone=? WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, abonent.getName());
            preparedStatement.setString(2, abonent.getSurname());
            preparedStatement.setString(3, abonent.getPhone());
            preparedStatement.setInt(4, abonent.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public Optional<Abonent> findByName(String name, String surname) throws DaoException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,name,surname,phone FROM abonents WHERE name=? AND surname=?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            ResultSet rs = null;
            rs = preparedStatement.executeQuery();
            Abonent abonent = null;
            while (rs.next()) {
                abonent = buildAbonent(rs);
                return Optional.ofNullable(abonent);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return Optional.empty();
    }

    public static AbonentDaoImpl getInstance() {
        return INSTANCE;
    }

    private Abonent buildAbonent(ResultSet rs) throws SQLException {
        return new Abonent(rs.getString("name"
        ), rs.getString("surname"), rs.getString("phone"));
    }


}
