package com.expressian.app2.repositories;

import com.expressian.app2.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {

    Store getByName (String name);

}
