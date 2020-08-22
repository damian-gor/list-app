package com.damgor.listapp.controllers;

import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.services.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RequestMapping("/productItem")
@RestController
public class ProductItemController {

    @Autowired
    private ProductItemService productItemService;

    @PostMapping
    public ProductItem addProductItem (@RequestBody ProductItem productItem) {
        return productItemService.addProductItem(productItem);
    }

    @DeleteMapping
    public HttpStatus removeProductItem (@PathParam("productItemId") Long productItemId) {
        productItemService.removeProductItem(productItemId);
        return HttpStatus.OK;
    }
}
