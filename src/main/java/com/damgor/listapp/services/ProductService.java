package com.damgor.listapp.services;

import com.damgor.listapp.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    List<Product> filterProductsByName(String name);
    Product addProduct(Product product);
    Product updateProduct(Product product);
    void removeProduct(Long productId);
}
