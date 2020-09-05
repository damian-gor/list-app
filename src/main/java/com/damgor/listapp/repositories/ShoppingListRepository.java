package com.damgor.listapp.repositories;

import com.damgor.listapp.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList,Long> {
    List<ShoppingList> findByBuyerId (Long buyerId);
}
