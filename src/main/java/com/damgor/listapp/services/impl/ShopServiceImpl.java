package com.damgor.listapp.services.impl;

import com.damgor.listapp.models.Shop;
import com.damgor.listapp.repositories.ShopRepository;
import com.damgor.listapp.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Shop getShop(Long shopId) {
        Shop shop = shopRepository.findById(shopId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Shop with ID " + shopId + " not found."));
        return shop;
    }

    @Override
    public Shop addShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop updateShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public void removeShop(Long shopId) {
        shopRepository.deleteById(shopId);
    }

    @Override
    public Shop getExistingShopByNameOrCreateNewOne(String shopName) {
        Optional<Shop> optionalShop = shopRepository.findOneByName(shopName);
        if (optionalShop.isPresent()) return optionalShop.get();
        else {
            Shop shop = new Shop();
            shop.setName(shopName);
            return shopRepository.save(shop);
        }
    }

    @Override
    public List<Shop> filterShopsByName(String shopName) {
        return shopRepository.findByNameContainingIgnoreCase(shopName);
    }

    @Override
    public Shop addShopIfNotExists(Shop shopToVerify) {
        Optional <Shop> optionalShop = shopRepository.findByNameIgnoreCase(shopToVerify.getName());
        return optionalShop.orElseGet(() -> addShop(shopToVerify));
    }

}
