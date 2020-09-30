package com.damgor.listapp.services;

import com.damgor.listapp.models.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> getAllShops();
    Shop addShop(Shop shop);
    Shop updateShop(Shop shop);
    void removeShop(Long shopId);
    Shop getExistingShopByNameOrCreateNewOne(String shopName);

}
