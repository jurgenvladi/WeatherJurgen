package org.sda.View;

import org.sda.Entity.Location;
import org.sda.Service.LocationService;

import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {

    private LocationService locationService;
    private Scanner scanner;

    public ConsoleUI(LocationService locationService) {
        this.locationService = locationService;
        this.scanner = new Scanner(System.in);
    }


    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addLocation();
                    break;

                case 8:
                    running = false;
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }





    private void displayMenu() {
        System.out.println("1. Add location");
        System.out.println("2. Display locations");
        System.out.println("3. Download weather data");
        System.out.println("4. Display weather data");
        System.out.println("5. Display statistics");
        System.out.println("6. Search location");
        System.out.println("7. Edit location");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }


    private void addLocation() {
        System.out.println("===== Add Model.Location =====");

        UUID id = UUID.randomUUID();
        System.out.println(STR."Generated location ID: \{id}");

        System.out.print("Enter city name: ");
        String cityName = scanner.nextLine();
        while (cityName.isEmpty()) {
            System.out.println("City name cannot be empty. Please enter a valid city name.");
            cityName = scanner.nextLine();
        }

        System.out.print("Enter region (optional): ");
        String region = scanner.nextLine();

        System.out.print("Enter country name: ");
        String countryName = scanner.nextLine();
        while (countryName.isEmpty()) {
            System.out.println("Country name cannot be empty. Please enter a valid country name.");
            countryName = scanner.nextLine();
        }

        Location location = new Location();
        location.setId(id);
        location.setCityName(cityName);
        location.setRegion(region);
        location.setCountryName(countryName);

        locationService.addLocation(location);
        System.out.println("Model.Location added successfully.");
    }


}
