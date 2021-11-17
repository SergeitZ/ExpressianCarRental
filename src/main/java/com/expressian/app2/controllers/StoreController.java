package com.expressian.app2.controllers;

import com.expressian.app2.models.Customer;
import com.expressian.app2.models.Store;
import com.expressian.app2.repositories.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    private StoreRepository repository;

    // Create
    @PostMapping
    public @ResponseBody ResponseEntity<Store> createStore (@RequestBody Store newStore) {
        return new ResponseEntity<>(repository.save(newStore), HttpStatus.CREATED);
    }

    // Read all
    @GetMapping
    public @ResponseBody List<Store> getStores() {
        return repository.findAll();
    }

    // Read by name
    @GetMapping("/search/{name}")
    public @ResponseBody ResponseEntity<Store> getStoreByName (@PathVariable String name) {
        if (repository.getByName(name) == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(repository.getByName(name), HttpStatus.OK);
    }


    // Read one by ID
    @GetMapping("/{id}")
    public @ResponseBody Store getOneStore (@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/customer")
    public @ResponseBody
    Store addCustomer (@RequestBody Store updates) {
        Store store = repository.findById(updates.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        store.customers.addAll(updates.customers);

        return repository.save(store);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> destroyStore (@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
