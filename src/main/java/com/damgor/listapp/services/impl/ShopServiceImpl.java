package com.damgor.listapp.services.impl;

import com.damgor.listapp.models.Shop;
import com.damgor.listapp.repositories.ShopRepository;
import com.damgor.listapp.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
