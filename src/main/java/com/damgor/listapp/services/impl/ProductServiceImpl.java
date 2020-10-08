package com.damgor.listapp.services.impl;

import com.damgor.listapp.models.Product;
import com.damgor.listapp.repositories.ProductRepository;
import com.damgor.listapp.services.ProductService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    private final Counter getAllProductsCounter;

    public ProductServiceImpl(MeterRegistry registry) {
        this.getAllProductsCounter = registry.counter("services.product-service.get-all-products");
    }

    @Override
    public List<Product> getAllProducts() {
        getAllProductsCounter.increment();
        return productRepository.findAll();
    }

    @Override
    public List<Product> filterProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public Product addProduct(Product product) {
        checkIfTheSameExists(product);
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setCategory(product.getCategory());
        newProduct.setUnit(product.getUnit());

        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Product product) {
        Product updatedProduct =
                productRepository
                        .findById(product.getId())
                        .orElseThrow(
                                () -> new EntityNotFoundException("Product with ID " + product.getId() + " not found."));
        updatedProduct.setName(product.getName());
        updatedProduct.setCategory(product.getCategory());
        updatedProduct.setUnit(product.getUnit());
        return productRepository.save(updatedProduct);
    }

    @Override
    public void removeProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    void checkIfTheSameExists(Product product) {
        if (productRepository.findOneByNameAndCategory(product.getName(), product.getCategory()).isPresent())
            throw new EntityExistsException(
                    "Product with name: \"" + product.getName() + "\" and category: \"" + product.getCategory() +
                            "\" already exists!");
    }

}




