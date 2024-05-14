package org.sda.Connection;



import org.sda.Entity.Location;
import org.sda.Repository.LocationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcLocationDao implements LocationRepository {




    @Override
    public void addLocation(Location location) {
        try (Connection connection = DriverManager.getConnection(Constants.DB_URL + Constants.DB_NAME, Constants.DB_USERNAME, Constants.DB_PASSWORD)) {
            String query = "INSERT INTO LOCATIONS (ID, CITY_NAME, REGION, COUNTRY_NAME) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, location.getId().toString());
            statement.setString(2, location.getCityName());
            statement.setString(3, location.getRegion());
            statement.setString(4, location.getCountryName());


            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public void updateLocation(Location location) {

        try (Connection connection = DriverManager.getConnection(Constants.DB_URL + Constants.DB_NAME, Constants.DB_USERNAME, Constants.DB_PASSWORD)) {
            String query = "UPDATE LOCATIONS SET CITY_NAME = ?, REGION = ?, COUNTRY_NAME = ? " +
                    "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, location.getCityName());
            statement.setString(2, location.getRegion());
            statement.setString(3, location.getCountryName());
            statement.setString(4, location.getId().toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteLocation(UUID locationId) {

        try (Connection connection = DriverManager.getConnection(Constants.DB_URL + Constants.DB_NAME, Constants.DB_USERNAME, Constants.DB_PASSWORD)) {
            String query = "DELETE FROM LOCATIONS WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, locationId.toString());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Location getLocation(UUID locationId) {
        Location location = null;
        try {
            Connection connection = DriverManager.getConnection(Constants.DB_URL + Constants.DB_NAME, Constants.DB_USERNAME, Constants.DB_PASSWORD);
            String query = "SELECT * FROM LOCATIONS WHERE ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, locationId.toString());
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                location = new Location();
                location.setId(UUID.fromString(resultSet.getString("ID")));
                location.setCityName(resultSet.getString("CITY_NAME"));
                location.setRegion(resultSet.getString("REGION"));
                location.setCountryName(resultSet.getString("COUNTRY_NAME"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return location;
    }

    @Override
    public Location getLocationByName(String cityName) {
        Location location = null;
        try {
            Connection connection = DriverManager.getConnection(Constants.DB_URL + Constants.DB_NAME, Constants.DB_USERNAME, Constants.DB_PASSWORD);
            String query = "SELECT * FROM LOCATIONS WHERE CITY_NAME = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, cityName);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                location = new Location();
                location.setId(UUID.fromString(resultSet.getString("ID")));
                location.setCityName(resultSet.getString("CITY_NAME"));
                location.setRegion(resultSet.getString("REGION"));
                location.setCountryName(resultSet.getString("COUNTRY_NAME"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return location;
    }

    @Override
    public List<Location> getAllLocations() {
        List<Location> locations = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(Constants.DB_URL + Constants.DB_NAME, Constants.DB_USERNAME, Constants.DB_PASSWORD);
            String query = "SELECT * FROM LOCATIONS";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                Location location = new Location();
                location.setId(UUID.fromString(resultSet.getString("ID")));
                location.setCityName(resultSet.getString("CITY_NAME"));
                location.setRegion(resultSet.getString("REGION"));
                location.setCountryName(resultSet.getString("COUNTRY_NAME"));

                locations.add(location);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locations;
    }
}
