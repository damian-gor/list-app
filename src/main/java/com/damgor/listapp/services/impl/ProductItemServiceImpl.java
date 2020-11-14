package com.damgor.listapp.services.impl;

import com.damgor.listapp.models.DTOs.ProductItemDTO;
import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.repositories.ProductItemRepository;
import com.damgor.listapp.security.services.UserDetailsServiceExt;
import com.damgor.listapp.services.ImageService;
import com.damgor.listapp.services.ProductItemService;
import com.damgor.listapp.services.common.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductItemServiceImpl implements ProductItemService {

    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private MapperService mapperService;
    @Autowired
    private UserDetailsServiceExt userDetailsService;
    @Autowired
    private ImageService imageService;

    @Override
    public ProductItem addProductItem(ProductItem productItem) {
        productItem.setOwnerId(userDetailsService.getUserDTO().getUserId());
        return productItemRepository.save(productItem);
    }

    @Override
    public ProductItem updateProductItem(ProductItem updatedProductItem) {
        return productItemRepository.save(updatedProductItem);
    }

    @Override
    public ProductItem addProductItem(ProductItemDTO newProductItemDTO) {
        newProductItemDTO.setAuthor(userDetailsService.getUserDTO());
        ProductItem newProductItem = productItemRepository.save(mapperService.productItemToEntity(newProductItemDTO));
        return newProductItem;
    }

    @Override
    public void removeProductItem(Long productItemId) {
        imageService.removeProductItemImage(productItemId);
        productItemRepository.deleteById(productItemId);
    }
}
