package org.sda.Service;

import lombok.AllArgsConstructor;
import org.sda.Entity.Location;
import org.sda.Repository.LocationRepository;


@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;

    public void addLocation(Location location) {
        locationRepository.addLocation(location);
    }
}
