package com.expressian.app2.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Integer age;
    private Boolean hasLicense;

//    @JsonManagedReference
    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "store_customer",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "store_id")
    )
    public Set<Store> stores;

    private Customer() {};

    public Customer(String name, Integer age, boolean hasLicense) {
        this.name = name;
        this.age = age;
        this.hasLicense = hasLicense;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isHasLicense() {
        return hasLicense;
    }

    public void setHasLicense(boolean hasLicense) {
        this.hasLicense = hasLicense;
    }
}
