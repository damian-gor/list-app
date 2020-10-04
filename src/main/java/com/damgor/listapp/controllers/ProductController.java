package com.damgor.listapp.controllers;

import com.damgor.listapp.models.Product;
import com.damgor.listapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

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

    @PutMapping
    public Product updateProduct (@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/{productId}")
    public HttpStatus removeProduct (@PathVariable("productId") Long productId) {
        productService.removeProduct(productId);
        return HttpStatus.OK;
    }
}
