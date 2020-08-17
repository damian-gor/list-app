package com.damgor.listapp.services.impl;

import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.repositories.ProductItemRepository;
import com.damgor.listapp.services.ProductItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductItemServiceImpl implements ProductItemService {

    @Autowired
    ProductItemRepository productItemRepository;

    @Override
    public ProductItem addProductItem(ProductItem productItem) {
        return productItemRepository.save(productItem);
    }
}
