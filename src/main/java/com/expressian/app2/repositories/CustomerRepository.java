package com.expressian.app2.repositories;

import com.expressian.app2.models.Customer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findAllByHasLicense (Boolean hasLicense, Sort sort);
}
