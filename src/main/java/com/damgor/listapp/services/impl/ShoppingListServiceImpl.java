package com.damgor.listapp.services.impl;

import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.models.ShoppingList;
import com.damgor.listapp.repositories.ShoppingListRepository;
import com.damgor.listapp.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    @Autowired private ShoppingListRepository shoppingListRepository;

    @Override
    public ShoppingList getShoppingList(Long id) {
        return shoppingListRepository.getOne(id);
    }

    @Override
    public ShoppingList addProductItemToShoppingList(ProductItem newProductItem, Long shoppingListId) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isPresent()) {
            ShoppingList updatedShoppingList = optionalShoppingList.get();
            List<ProductItem> updatedProductsList = updatedShoppingList.getProductsList();
            updatedProductsList.add(newProductItem);
            updatedShoppingList.setProductsList(updatedProductsList);
            return shoppingListRepository.save(updatedShoppingList);
        }
        else throw new EntityExistsException("ShoppingList with id: " + shoppingListId + " doesn't exist!");
    }
}
