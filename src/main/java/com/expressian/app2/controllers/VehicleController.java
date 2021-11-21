package com.expressian.app2.controllers;

import com.expressian.app2.models.Location;
import com.expressian.app2.models.Vehicle;
import com.expressian.app2.repositories.LocationRepository;
import com.expressian.app2.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleRepository repository;

    @Autowired
    private LocationRepository locationRepository;

    // Create
    @PostMapping
    public @ResponseBody ResponseEntity<Vehicle> createVehicle (@RequestBody Vehicle newVehicle) {
        return new ResponseEntity<>(repository.save(newVehicle), HttpStatus.CREATED);
    }

    @PostMapping("/location")
    public Vehicle addLocation (@RequestBody Vehicle car) {
        Vehicle vehicle = repository.findById(car.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(vehicle.getLocation() != null) {
            Location oldLocation = vehicle.getLocation();
            vehicle.setLocation(null);
            locationRepository.delete(oldLocation);
        }
        Location location = locationRepository.save(car.getLocation());
        vehicle.setLocation(location);
        return repository.save(vehicle);
    }

    // Read all
    @GetMapping
    public @ResponseBody List<Vehicle> getVehicles() {
        return repository.findAll();
    }

    // Read all by make
    @GetMapping("/make/{make}")
    public ResponseEntity<List<Vehicle>> getVehicleByMake (@PathVariable String make) {
        return new ResponseEntity<>(repository.findAllByMake(make, Sort.by("model")), HttpStatus.OK);
    }

    // Read one by ID
    @GetMapping("/{id}")
    public @ResponseBody Vehicle getOneVehicle (@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Update by ID
    @PutMapping("/{id}")
    public @ResponseBody Vehicle updateVehicle (@PathVariable Long id, @RequestBody Vehicle updates) {
        Vehicle vehicle = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getMake() != null) vehicle.setMake(updates.getMake());
        if (updates.getModel() != null) vehicle.setModel(updates.getModel());
        if (updates.getYear() != null) vehicle.setYear(updates.getYear());

        return repository.save(vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle (@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

}
