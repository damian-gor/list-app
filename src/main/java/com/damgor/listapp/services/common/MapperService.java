package com.damgor.listapp.services.common;

import com.damgor.listapp.models.DTOs.ProductItemDTO;
import com.damgor.listapp.models.DTOs.ShoppingListDTO;
import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.models.ShoppingList;

import java.util.List;

public interface MapperService {
    ProductItemDTO productItemToDto(ProductItem productItem);

    ProductItem productItemToEntity(ProductItemDTO productItemDTO);

    List<ShoppingListDTO> shoppingListToDTO(List<ShoppingList> shoppingLists);

    ShoppingListDTO shoppingListToDTO(ShoppingList shoppingList);

    ShoppingList shoppingListToEntity(ShoppingListDTO shoppingListDTO);
}
