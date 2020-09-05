package com.damgor.listapp.controllers;

import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.models.ShoppingList;
import com.damgor.listapp.security.services.UserDetailsServiceExt;
import com.damgor.listapp.services.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/shoppingList")
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;
    @Autowired
    private UserDetailsServiceExt userDetailsService;

    @GetMapping
    public List<ShoppingList> getAllShoppingLists(@RequestParam(required = false, name = "buyerId") Long buyerId) {
        if (buyerId != null) return shoppingListService.getShoppingListsByBuyerId(buyerId);
        else return shoppingListService.getAllShoppingLists();
    }

    @GetMapping("/{shoppingListId}")
    public ShoppingList getShoppingList(@PathVariable Long shoppingListId) {
        return shoppingListService.getShoppingList(shoppingListId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void deleteShoppingList(@PathParam("shoppingListId") Long shoppingListId) {
        shoppingListService.deleteShoppingList(shoppingListId);
    }

    @PutMapping
    @RequestMapping("/addProductItemToList")
    public ShoppingList addProductItemToShoppingList(@RequestBody ProductItem newProductItem,
                                                     @PathParam("shoppingListId") Long shoppingListId) {
        return shoppingListService.addProductItemToShoppingList(newProductItem, shoppingListId);
    }

    @PatchMapping
    @RequestMapping("/updateProductItemInList")
    public ShoppingList updateProductItemInShoppingList(@RequestBody ProductItem updatedProductItem,
                                                        @PathParam("shoppingListId") Long shoppingListId) {

        return shoppingListService.updateProductItemInShoppingList(updatedProductItem, shoppingListId);
    }

    @PutMapping
    @RequestMapping("/removeProductItemFromList")
    public HttpStatus removeProductItemFromList(@RequestBody ProductItem removedProductItem,
                                                @PathParam("shoppingListId") Long shoppingListId) {
        shoppingListService.removeProductItemFromList(removedProductItem, shoppingListId);
        return HttpStatus.OK;
    }

}
