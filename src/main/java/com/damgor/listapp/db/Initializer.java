package com.damgor.listapp.db;

import com.damgor.listapp.models.*;
import com.damgor.listapp.models.enums.ProductCategory;
import com.damgor.listapp.models.enums.ProductStatus;
import com.damgor.listapp.models.enums.ProductUnit;
import com.damgor.listapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Initializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private UserRepository userRepository;

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

            Shop shop1 = new Shop(1L, "Biedronka", "biedronka.pl");
            Shop shop2 = new Shop(2L, "Lidl", "lidl.pl");
            Shop shop3 = new Shop(3L, "Auchan", "auchan.pl");
            shopRepository.saveAll(Arrays.asList(shop1,shop2,shop3));

            userRepository.saveAll(Arrays.asList(
               new User("Damian","damian.gorka94@gmail.com","$2a$10$/LONzloFszZa7d19s2vcAOA.dkiU5qbjqmaUFJCxgQMJ.0VnqFtk2"),
               new User("Daria","daria@gmail.com","$2a$10$/LONzloFszZa7d19s2vcAOA.dkiU5qbjqmaUFJCxgQMJ.0VnqFtk2"),
               new User("Ania","ania@gmail.com","$2a$10$/LONzloFszZa7d19s2vcAOA.dkiU5qbjqmaUFJCxgQMJ.0VnqFtk2")
            ));


            ShoppingList shoppingList = new ShoppingList(1L, productItems, shop1, new ArrayList<>(Arrays.asList((userRepository.getOne(2L)),userRepository.getOne(3L))));
            shoppingListRepository.save(shoppingList);
        }
    }
}