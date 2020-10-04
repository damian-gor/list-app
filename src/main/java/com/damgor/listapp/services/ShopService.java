package com.damgor.listapp.services;

import com.damgor.listapp.models.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> getAllShops();
    Shop getShop(Long shopId);
    Shop addShop(Shop shop);
    Shop updateShop(Shop shop);
    void removeShop(Long shopId);
    Shop getExistingShopByNameOrCreateNewOne(String shopName);
    List<Shop>filterShopsByName(String shopName);
    Shop addShopIfNotExists(Shop shopToVerify);
}
