package com.expressian.app2.controllers;

import com.expressian.app2.models.Vehicle;
import com.expressian.app2.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
    @Autowired
    private VehicleRepository repository;

    // Create
    @PostMapping
    public @ResponseBody Vehicle createVehicle (@RequestBody Vehicle newVehicle) {
        return repository.save(newVehicle);
    }

    // Read all
    @GetMapping
    public @ResponseBody List<Vehicle> getVehicles() {
        return repository.findAll();
    }

    // Read one by ID
    @GetMapping("/{id}")
    public @ResponseBody Vehicle getOneVehicle (@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
