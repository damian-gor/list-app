package com.damgor.listapp.services;

import com.damgor.listapp.models.ProductItem;

public interface ProductItemService {
    ProductItem addProductItem(ProductItem productItem);
    ProductItem updateProductItem (ProductItem updatedProductItem);
    void removeProductItem(Long productItemId);
}
