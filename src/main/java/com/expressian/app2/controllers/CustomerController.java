package com.expressian.app2.controllers;

import com.expressian.app2.models.Customer;
import com.expressian.app2.repositories.CustomerRepository;
import jdk.javadoc.doclet.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    // Create
    @PostMapping
    public @ResponseBody ResponseEntity<Customer> createCustomer (@RequestBody Customer newCustomer) {
        return new ResponseEntity<>(repository.save(newCustomer), HttpStatus.CREATED);
    }

    // Read all
    @GetMapping
    public @ResponseBody List<Customer> getCustomers() {
        return repository.findAll();
    }

    // Read all by License status
    //TODO: endpoints more user friendly ie: license/yes or no
    @GetMapping("/license/{hasLicense}")
    public ResponseEntity<List<Customer>> getCustomerByHasLicense (@PathVariable Boolean hasLicense) {
        return new ResponseEntity<>(repository.findAllByHasLicense(hasLicense, Sort.by("name")), HttpStatus.OK);
    }

    // Read one by ID
    @GetMapping("/{id}")
    public @ResponseBody Customer getOneCustomer (@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    // Update by ID
    @PutMapping("/{id}")
    public @ResponseBody Customer updateCustomer (@PathVariable Long id, @RequestBody Customer updates) {
        Customer customer = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (updates.getName() != null) customer.setName(updates.getName());
        if (updates.getAge() != null) customer.setAge(updates.getAge());
        if (updates.stores != null) customer.stores = updates.stores;
        // TODO: needs revision, if statement not needed for boolean?
        customer.setHasLicense(updates.isHasLicense());

        return repository.save(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer (@PathVariable Long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
