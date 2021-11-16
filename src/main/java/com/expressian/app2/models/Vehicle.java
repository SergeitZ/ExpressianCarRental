package com.expressian.app2.models;

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

    public Vehicle() {};

    public Vehicle(String make, String model, Integer year, Store store) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.store = store;
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
}
