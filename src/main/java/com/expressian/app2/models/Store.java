package com.expressian.app2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Store {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    @JsonManagedReference
    @OneToMany
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private List<Vehicle> vehicles;

    @JsonManagedReference
//    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "store_customer",
            joinColumns = @JoinColumn(name = "store_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    public Set<Customer> customers;

    private Store() {}

    public Store(String name, List<Vehicle> vehicles, Set<Customer> customers) {
        this.name = name;
        this.vehicles = vehicles;
        this.customers = customers;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
