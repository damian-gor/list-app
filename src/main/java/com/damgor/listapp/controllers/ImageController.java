package com.damgor.listapp.controllers;

import com.damgor.listapp.models.ProductItemImage;
import com.damgor.listapp.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("images/product-items")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/{productItemId}")
    public HttpStatus uploadProductItemImage(@RequestParam("imageFile") MultipartFile file,
                                             @PathVariable("productItemId") Long productItemId) {
        imageService.uploadProductItemImage(productItemId, file);
        return HttpStatus.OK;
    }

    @GetMapping("/{productItemId}")
    public ProductItemImage getProductItemImage(@PathVariable("productItemId") Long productItemId) {
        return imageService.getProductItemImage(productItemId);
    }

    @DeleteMapping("/{productItemId}")
    public HttpStatus removeProductItemImage(@PathVariable("productItemId") Long productItemId) {
        imageService.removeProductItemImage(productItemId);
        return HttpStatus.OK;
    }

    @GetMapping
    public List<Long> getIdsOfProductItemsWithImages(@RequestBody List<Long> productsOnListIds) {
        return imageService.getIdsOfProductItemsWithImages(productsOnListIds);
    }
}
