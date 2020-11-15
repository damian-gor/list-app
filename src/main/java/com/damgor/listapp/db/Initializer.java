package com.damgor.listapp.db;

import com.damgor.listapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
//        if (!productRepository.findById(10L).isPresent()) {
//            productRepository.saveAll(Arrays.asList(
//                    new Product("Ogórki", ProductCategory.WARZYWA_OWOCE_SWIEZE, ProductUnit.SZT),
//                    new Product("Banany", ProductCategory.WARZYWA_OWOCE_SWIEZE, ProductUnit.SZT),
//                    new Product("Szpinak", ProductCategory.WARZYWA_OWOCE_PAKOWANE, ProductUnit.OPK),
//                    new Product("Truskawki", ProductCategory.MROZONKI, ProductUnit.KG),
//                    new Product("Piwo", ProductCategory.ALKOHOL, ProductUnit.SZT),
//                    new Product("Kurczak pierś", ProductCategory.MIESO, ProductUnit.KG),
//                    new Product("Mleko 2%", ProductCategory.NABIAL, ProductUnit.L),
//                    new Product("Nerkowiec", ProductCategory.ORZECHY_NASIONA, ProductUnit.G),
//                    new Product("Gumy Orbit zielone", ProductCategory.INNE, ProductUnit.OPK),
//                    new Product("Mąka żytnia", ProductCategory.PRODUKTY_ZBOZOWE, ProductUnit.KG),
//                    new Product("Czekolada gorzka 64%", ProductCategory.SLODYCZE, ProductUnit.SZT),
//                    new Product("Pieluszki Dada 4+", ProductCategory.HIGIENA, ProductUnit.OPK))
//            );
//        }
//        if (!shoppingListRepository.findById(1L).isPresent()) {
//            List<ProductItem> productItems = new ArrayList<>(Arrays.asList(
//                    new ProductItem(2.0, ProductUnit.SZT, "Mleko 0,5%", ProductCategory.NABIAL, 1L),
//                    new ProductItem(4.0, ProductUnit.SZT, "Piwo", ProductCategory.ALKOHOL, 1L, ProductStatus.BOUGHT),
//                    new ProductItem(1.0, ProductUnit.SZT, "Chleb razowy", ProductCategory.PIECZYWO, 1L),
//                    new ProductItem(0.5, ProductUnit.KG, "Cytryny", ProductCategory.WARZYWA_OWOCE_SWIEZE, 1L, ProductStatus.NOT_AVAILABLE),
//                    new ProductItem(200.0, ProductUnit.G, "Ser zółty", ProductCategory.NABIAL, 1L)
//            ));
//
//            productItems = productItemRepository.saveAll(productItems);
//
//            Shop shop1 = new Shop(1L, "Biedronka", "https://www.biedronka.pl/pl/gazetki");
//            Shop shop2 = new Shop(2L, "Lidl", "https://www.lidl.pl/informacje-dla-klienta/nasze-gazetki");
//            Shop shop3 = new Shop(3L, "Auchan", "https://www.auchan.pl/pl/gazetki");
//            shopRepository.saveAll(Arrays.asList(shop1,shop2,shop3));
//
//            ShoppingList shoppingList = new ShoppingList(1L, productItems, shop1, new ArrayList<>(Arrays.asList((userRepository.getOne(2L)),userRepository.getOne(3L))));
//            shoppingListRepository.save(shoppingList);
//        }
    }
}