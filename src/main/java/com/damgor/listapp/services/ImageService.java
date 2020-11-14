package com.damgor.listapp.services;


import com.damgor.listapp.models.ProductItemImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {

    void uploadProductItemImage(Long productItemId, MultipartFile file);

    ProductItemImage getProductItemImage(Long productItemId);

    List<Long> getIdsOfProductItemsWithImages(List<Long> productsOnListIds);

    void removeProductItemImage(Long productItemId);
}
