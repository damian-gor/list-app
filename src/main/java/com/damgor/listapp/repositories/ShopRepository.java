package com.damgor.listapp.repositories;

import com.damgor.listapp.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long> {

    Optional<Shop> findOneByName (String shopName);
    List<Shop> findByNameContainingIgnoreCase(String shopName);
    Optional<Shop> findByNameIgnoreCase(String shopName);
}
