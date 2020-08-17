package com.damgor.listapp.db;

import com.damgor.listapp.models.Product;
import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.models.ShoppingList;
import com.damgor.listapp.models.enums.ProductCategory;
import com.damgor.listapp.models.enums.ProductStatus;
import com.damgor.listapp.models.enums.ProductUnit;
import com.damgor.listapp.repositories.ProductItemRepository;
import com.damgor.listapp.repositories.ProductRepository;
import com.damgor.listapp.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class Initializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private ShoppingListRepository shoppingListRepository;

    @Override
    public void run(String... args) throws Exception {
        if (!productRepository.findById(10L).isPresent()) {
            productRepository.saveAll(Arrays.asList(
                    new Product("Ogórki", ProductCategory.WARZYWA_OWOCE_SWIEZE),
                    new Product("Banany", ProductCategory.WARZYWA_OWOCE_SWIEZE),
                    new Product("Szpinak", ProductCategory.WARZYWA_OWOCE_PAKOWANE),
                    new Product("Truskawki", ProductCategory.MROZONKI),
                    new Product("Piwo", ProductCategory.ALKOHOL),
                    new Product("Kurczak pierś", ProductCategory.MIESO),
                    new Product("Mleko 2%", ProductCategory.NABIAL),
                    new Product("Nerkowiec", ProductCategory.ORZECHY_NASIONA),
                    new Product("Gumy Orbit zielone", ProductCategory.INNE),
                    new Product("Mąka żytnia", ProductCategory.PRODUKTY_ZBOZOWE),
                    new Product("Czekolada gorzka 64%", ProductCategory.SLODYCZE),
                    new Product("Pieluszki Dada 4+", ProductCategory.HIGIENA))
            );
        }
        if (!shoppingListRepository.findById(1L).isPresent()) {
            List<ProductItem> productItems = new ArrayList<>(Arrays.asList(
                    new ProductItem(2.0, ProductUnit.SZT, "Mleko 0,5%", ProductCategory.NABIAL, 1L),
                    new ProductItem(4.0, ProductUnit.SZT, "Piwo", ProductCategory.ALKOHOL, 1L, ProductStatus.BOUGHT),
                    new ProductItem(1.0, ProductUnit.SZT, "Chleb razowy", ProductCategory.PIECZYWO, 1L),
                    new ProductItem(0.5, ProductUnit.KG, "Cytryny", ProductCategory.WARZYWA_OWOCE_SWIEZE, 1L, ProductStatus.NOT_AVAILABLE),
                    new ProductItem(200.0, ProductUnit.G, "Ser zółty", ProductCategory.NABIAL, 1L)
            ));

            productItems = productItemRepository.saveAll(productItems);

            ShoppingList shoppingList = new ShoppingList(1L, productItems, null, "Biedronka");
            shoppingListRepository.save(shoppingList);
        }
    }
}