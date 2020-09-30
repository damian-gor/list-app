package com.damgor.listapp.controllers;

import com.damgor.listapp.models.Product;
import com.damgor.listapp.security.services.UserDetailsServiceExt;
import com.damgor.listapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private UserDetailsServiceExt userDetailsService;

    @GetMapping
    public List<Product> getAllProducts () {
        return productService.getAllProducts();
    }

    @GetMapping("/filterByName")
    public List<Product> filterProductsByName (@PathParam("name") String name) {
        return productService.filterProductsByName(name);
    }

    @PostMapping
    public Product addProduct (@RequestBody Product product) {
        return productService.addProduct(product);
    }
}
