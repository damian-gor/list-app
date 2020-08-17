package com.damgor.listapp.repositories;

import com.damgor.listapp.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingList,Long> {
}
