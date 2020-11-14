package com.damgor.listapp.services.impl;

import com.damgor.listapp.models.ProductItemImage;
import com.damgor.listapp.repositories.ProductItemImageRepository;
import com.damgor.listapp.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ProductItemImageRepository productItemImageRepository;

    @Override
    public void uploadProductItemImage(Long productItemId, MultipartFile file) {
        Optional<ProductItemImage> optionalProductItemImage = productItemImageRepository.findByProductItemId(productItemId);
        ProductItemImage productItemImage = optionalProductItemImage.orElseGet(ProductItemImage::new);
        productItemImage.setProductItemId(productItemId);
        try {
            productItemImage.setPicByte(compressBytes(file.getBytes()));
            productItemImageRepository.save(productItemImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductItemImage getProductItemImage(Long productItemId) {
        Optional<ProductItemImage> retrivedImage = productItemImageRepository.findByProductItemId(productItemId);
        if (retrivedImage.isPresent()) {
            ProductItemImage productItemImage = new ProductItemImage();
            productItemImage.setId(retrivedImage.get().getId());
            productItemImage.setProductItemId(retrivedImage.get().getProductItemId());
            productItemImage.setPicByte(decompressBytes(retrivedImage.get().getPicByte()));
            return productItemImage;
        } else throw new EntityNotFoundException("Nie odnaleziono obrazu dla ProductItem o ID " + productItemId);
    }

    @Override
    public List<Long> getIdsOfProductItemsWithImages(List<Long> productsOnListIds) {
        return productItemImageRepository.getIdsOfProductItemsWithImages(productsOnListIds);
    }

    @Override
    public void removeProductItemImage(Long productItemId) {
        productItemImageRepository.deleteByProductItemId(productItemId);
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
