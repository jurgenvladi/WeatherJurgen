package org.sda;

import org.sda.Connection.DatabaseCreator;
import org.sda.Connection.JdbcLocationDao;
import org.sda.Repository.LocationRepository;
import org.sda.Service.LocationService;
import org.sda.View.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        DatabaseCreator databaseCreator = new DatabaseCreator();
        LocationRepository locationRepository = new JdbcLocationDao();
        LocationService locationService = new LocationService(locationRepository);
        ConsoleUI consoleUI = new ConsoleUI(locationService);
        consoleUI.start();

    }
}