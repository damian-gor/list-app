package com.damgor.listapp.controllers;

import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.models.ShoppingList;
import com.damgor.listapp.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/shoppingList")
@CrossOrigin(origins = "http://localhost:4200")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping
    public ShoppingList getShoppingList () {
        return shoppingListService.getShoppingList(1L);
    }

    @PatchMapping
    public ShoppingList addProductItemToShoppingList(@RequestBody ProductItem newProductItem,
                                                     @PathParam("shoppingListId") Long shoppingListId){
        return shoppingListService.addProductItemToShoppingList(newProductItem,shoppingListId);
    }
}
