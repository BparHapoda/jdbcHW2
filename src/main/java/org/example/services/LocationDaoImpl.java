package org.example.services;

import org.example.dao.LocationDao;
import org.example.entity.Location;
import org.example.exceptions.DaoException;
import org.example.utils.DataBaseConnection;
import org.example.utils.DatabaseRead;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationDaoImpl implements LocationDao {
    @Override
    public List<Location> findAll() throws DaoException {
        Connection connection = DataBaseConnection.getConnection();
        List<Location> locations = new ArrayList<>();
        ResultSet rs = null;
        try {
            rs = connection.createStatement().executeQuery("SELECT * FROM locations");
            while (rs.next()) {
                locations.add(buildLocation(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return locations;
    }

    @Override
    public Location findById(Integer id) throws DaoException {
        ResultSet rs = DatabaseRead.getResultSet(String.format("SELECT * FROM locations WHERE id=%d", id));
        try {
            return new Location(rs.getInt("id"), rs.getString("country"), rs.getString("city"),
                    rs.getString("address"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Location entity) throws DaoException {

    }

    @Override
    public Location save(Location entity) throws DaoException {
        return null;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    public Location buildLocation(ResultSet rs) throws SQLException {
        return new Location(rs.getInt("id"), rs.getString("country"),
                rs.getString("city"), rs.getString("address"));
    }
}
