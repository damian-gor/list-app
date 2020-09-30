package com.damgor.listapp.controllers;

import com.damgor.listapp.models.DTOs.ProductItemDTO;
import com.damgor.listapp.models.DTOs.ShoppingListDTO;
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
    public List<ShoppingListDTO> getAllShoppingLists(@RequestParam(required = false, name = "buyerId") Long buyerId) {
        if (buyerId != null) return shoppingListService.getShoppingListsByBuyerId(buyerId);
        else return shoppingListService.getAllShoppingLists();
    }

    @GetMapping("/userShoppingLists")
    public List<ShoppingListDTO> getUserShoppingLists() {
        return shoppingListService.getUserShoppingLists();
    }

    @PostMapping
    public ShoppingListDTO addShoppingList (@RequestBody ShoppingListDTO shoppingListDTO) {
       return shoppingListService.addShoppingList(shoppingListDTO);
    }

    @PutMapping
    public ShoppingListDTO updateShoppingList (@RequestBody ShoppingListDTO shoppingListDTO) {
       return shoppingListService.updateShoppingList(shoppingListDTO);
    }

    @GetMapping("/{shoppingListId}")
    public ShoppingListDTO getShoppingList(@PathVariable Long shoppingListId) {
        return shoppingListService.getShoppingList(shoppingListId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void deleteShoppingList(@PathParam("shoppingListId") Long shoppingListId) {
        shoppingListService.deleteShoppingList(shoppingListId);
    }

    @PutMapping
    @RequestMapping("/addProductItemToList")
    public ShoppingListDTO addProductItemToShoppingList(@RequestBody ProductItemDTO newProductItemDTO,
                                                     @PathParam("shoppingListId") Long shoppingListId) {
        return shoppingListService.addProductItemToShoppingList(newProductItemDTO, shoppingListId);
    }

    @PatchMapping
    @RequestMapping("/updateProductItemInList")
    public ShoppingListDTO updateProductItemInShoppingList(@RequestBody ProductItemDTO updatedProductItem,
                                                        @PathParam("shoppingListId") Long shoppingListId) {

        return shoppingListService.updateProductItemInShoppingList(updatedProductItem, shoppingListId);
    }

    @PutMapping
    @RequestMapping("/removeProductItemFromList")
    public HttpStatus removeProductItemFromList(@RequestBody ProductItemDTO removedProductItem,
                                                @PathParam("shoppingListId") Long shoppingListId) {
        shoppingListService.removeProductItemFromList(removedProductItem, shoppingListId);
        return HttpStatus.OK;
    }

}
