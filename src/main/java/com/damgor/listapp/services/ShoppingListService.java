package com.damgor.listapp.services;

import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.models.ShoppingList;

public interface ShoppingListService {
    ShoppingList getShoppingList(Long id);
    ShoppingList addProductItemToShoppingList(ProductItem newProductItem, Long shoppingListId);
}
