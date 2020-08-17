package com.damgor.listapp.controllers;

import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.services.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/productItem")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductItemController {

    @Autowired
    private ProductItemService productItemService;

    @PostMapping
    public ProductItem addProductItem (@RequestBody ProductItem productItem) {
        return productItemService.addProductItem(productItem);
    }

}
