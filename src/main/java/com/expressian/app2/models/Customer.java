package com.expressian.app2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private Integer age;
    private Boolean hasLicense;

    private Customer() {};

    public Customer(String name, Integer age, boolean hasLicense) {
        this.name = name;
        this.age = age;
        this.hasLicense = hasLicense;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public boolean isHasLicense() {
        return hasLicense;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setHasLicense(boolean hasLicense) {
        this.hasLicense = hasLicense;
    }
}
