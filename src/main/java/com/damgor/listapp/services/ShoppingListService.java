package com.damgor.listapp.services;

import com.damgor.listapp.models.DTOs.ProductItemDTO;
import com.damgor.listapp.models.DTOs.ShoppingListDTO;

import java.util.List;

public interface ShoppingListService {
    ShoppingListDTO getShoppingList(Long id);
    ShoppingListDTO addProductItemToShoppingList(ProductItemDTO newProductItemDTO, Long shoppingListId);
    ShoppingListDTO updateProductItemInShoppingList (ProductItemDTO updatedProductItem, Long shoppingListId);
    void removeProductItemFromList (ProductItemDTO removedProductItem, Long shoppingListId);
    List<ShoppingListDTO> getShoppingListsByBuyerId(Long buyerId);
    List<ShoppingListDTO> getAllShoppingLists();
    List<ShoppingListDTO> getAllShoppingListsConnectedWithUser();
    List<ShoppingListDTO> getUserShoppingLists();
    void deleteShoppingList (Long shoppingListId);
    ShoppingListDTO addShoppingList(ShoppingListDTO shoppingListDTO);
    ShoppingListDTO updateShoppingList(ShoppingListDTO shoppingListDTO);

}
