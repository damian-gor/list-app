package com.damgor.listapp.services;

import com.damgor.listapp.models.Product;

import java.util.List;

public interface ProductService {
//    ShoppingListDTO getAllProducts();
    List<Product> getAllProducts();
    Product addProduct(Product product);
}
