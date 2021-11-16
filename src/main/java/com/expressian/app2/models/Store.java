package com.expressian.app2.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Store {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private List<Vehicle> vehicles;
    private String name;

    private Store() {}

    public Store(String name, List<Vehicle> vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }

    public long getId() {
        return id;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
