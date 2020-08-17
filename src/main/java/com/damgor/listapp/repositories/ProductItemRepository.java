package com.damgor.listapp.repositories;

import com.damgor.listapp.models.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
}
