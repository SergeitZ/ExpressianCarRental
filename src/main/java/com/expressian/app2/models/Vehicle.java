package com.expressian.app2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private long id;
    private String make;
    private String model;
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;

    @OneToOne
    @JsonIgnoreProperties("vehicle")
    private Location location;

    public Vehicle() {};

    public Vehicle(String make, String model, Integer year, Store store, Location location) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.store = store;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Integer getYear() {
        return year;
    }

    public Store getStore() {
        return store;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
