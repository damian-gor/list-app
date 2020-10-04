package com.damgor.listapp.controllers;

import com.damgor.listapp.models.Shop;
import com.damgor.listapp.services.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RequestMapping("/shops")
@RestController
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping
    public List<Shop> getAllShops () {
        return shopService.getAllShops();
    }

    @GetMapping("/{shopId}")
    public Shop getShop (@PathVariable("shopId") Long shopId) {
        return shopService.getShop(shopId);
    }

    @PostMapping
    public Shop addShop (@RequestBody Shop shop) {
        return shopService.addShop(shop);
    }

    @PutMapping
    public Shop updateShop (@RequestBody Shop shop) {
        return shopService.updateShop(shop);
    }

    @DeleteMapping("/{shopId}")
    public HttpStatus removeShop (@PathVariable("shopId") Long shopId) {
        shopService.removeShop(shopId);
        return HttpStatus.OK;
    }

    @GetMapping("/filterByName")
    public List<Shop> filterShopsByName (@PathParam("shopName") String shopName) {
        return shopService.filterShopsByName(shopName);
    }
}
