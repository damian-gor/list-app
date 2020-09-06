package com.damgor.listapp.services;

import com.damgor.listapp.models.DTOs.ProductItemDTO;
import com.damgor.listapp.models.ProductItem;

public interface ProductItemService {
    ProductItem addProductItem(ProductItem productItem);
    ProductItem updateProductItem (ProductItem updatedProductItem);
    ProductItem addProductItem(ProductItemDTO newProductItemDTO);
    ProductItem updateProductItem (ProductItemDTO updatedProductItemDTO);
    void removeProductItem(Long productItemId);
}
