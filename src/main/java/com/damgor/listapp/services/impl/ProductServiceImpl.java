package com.damgor.listapp.services.impl;

import com.damgor.listapp.models.Product;
import com.damgor.listapp.repositories.ProductRepository;
import com.damgor.listapp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

//    @Override
//    public List<ProductDTO> getAllProducts() {
//        List<Product> products = productRepository.findAll();
//
//        List<ProductDTO> productDTOList = new ArrayList<>();
//        products.forEach(p -> {
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setId(p.getId());
//            productDTO.setProductName(p.getName());
//            productDTOList.add(productDTO);
//        });
//
//        return productDTOList;
//    }
//
//    @Override
//    public ShoppingListDTO getAllProducts() {
//        List<Product> products = productRepository.findAll();
//
//        List<ProductDTO> productDTOList = new ArrayList<>();
//        products.forEach(p -> {
//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setId(p.getId());
//            productDTO.setProductName(p.getName());
//            productDTOList.add(productDTO);
//        });
//
//        ShoppingListDTO shoppingListDTO = new ShoppingListDTO();
//        shoppingListDTO.setProductsList(productDTOList);
//
//        return shoppingListDTO;
//    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
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

    void checkIfTheSameExists(Product product) {
        if (productRepository.findOneByNameAndCategory(product.getName(), product.getCategory()).isPresent())
            throw new EntityExistsException(
                    "Product with name: \"" + product.getName() + "\" and category: \"" + product.getCategory() +
                            "\" already exists!");
    }


}




