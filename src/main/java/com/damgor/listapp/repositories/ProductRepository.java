package com.damgor.listapp.repositories;

import com.damgor.listapp.models.Product;
import com.damgor.listapp.models.enums.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findOneByNameAndCategory (String productName, ProductCategory category);
}
