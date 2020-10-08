package com.damgor.listapp.repositories;

import com.damgor.listapp.models.ShoppingList;
import com.damgor.listapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

  //    Lista ShoppingLists, których User jest twórcą
  List<ShoppingList> findByBuyerId(Long buyerId);

  //    Lista ShoppingLists, których user jest współuczestnikiem
  List<ShoppingList> findByParticipantsListIsContaining(User user);

  //    Lista ShoppingLists, których user jest współuczestnikiem bądź twórcą
  List<ShoppingList> findByParticipantsListIsContainingOrBuyerId(User user, Long buyerId);
}
