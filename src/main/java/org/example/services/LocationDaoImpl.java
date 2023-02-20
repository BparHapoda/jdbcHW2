package org.example.services;

import org.example.dao.LocationDao;
import org.example.entity.Abonent;
import org.example.entity.Location;
import org.example.exceptions.DaoException;
import org.example.utils.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LocationDaoImpl implements LocationDao {

    private final static LocationDaoImpl INSTANCE = new LocationDaoImpl();

    @Override
    public List<Location> findAll() throws DaoException {
        List<Location> locations = new ArrayList<>();
        try (
                Connection connection = DataBaseConnection.getConnection()
        ) {

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id,city,country,address FROM locations", Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                locations.add(buildLocation(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }

    @Override
    public Optional<Location> findById(Integer id) throws DaoException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id,city,country,address FROM locations WHERE id=?", Statement.RETURN_GENERATED_KEYS
            );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Location location = buildLocation(resultSet);
            return Optional.ofNullable(location);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public Location save(Location location) throws DaoException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO locations (country,city,address) " +
                            "VALUES ((?),(?),(?));",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, location.getCountry());
            preparedStatement.setString(2, location.getCity());
            preparedStatement.setString(3, location.getAddress());
            preparedStatement.executeUpdate();
            ResultSet rs= preparedStatement.getGeneratedKeys();
            while (rs.next()){
            location.setId(rs.getInt("id"));}
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return location;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM locations WHERE id=?", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return preparedStatement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void update(Location location) throws DaoException {
        try(Connection connection=DataBaseConnection.getConnection()){
            PreparedStatement preparedStatement=connection.prepareStatement("UPDATE locations SET country=?,city=?,address=? WHERE id=?",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,location.getCountry());
            preparedStatement.setString(2,location.getCity());
            preparedStatement.setString(3,location.getAddress());
            preparedStatement.setInt(4,location.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){throw new RuntimeException();}

    }

    public Location buildLocation(ResultSet rs) throws SQLException {
        return new Location(rs.getString("country"),
                rs.getString("city"), rs.getString("address"));
    }
    public static LocationDaoImpl getInstance() {
        return INSTANCE;
    }
}
