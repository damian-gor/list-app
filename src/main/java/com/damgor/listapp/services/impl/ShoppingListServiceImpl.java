package com.damgor.listapp.services.impl;

import com.damgor.listapp.models.DTOs.ProductItemDTO;
import com.damgor.listapp.models.DTOs.ShoppingListDTO;
import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.models.ShoppingList;
import com.damgor.listapp.repositories.ShoppingListRepository;
import com.damgor.listapp.security.services.UserDetailsServiceExt;
import com.damgor.listapp.services.ProductItemService;
import com.damgor.listapp.services.ShoppingListService;
import com.damgor.listapp.services.common.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ProductItemService productItemService;
    @Autowired
    private UserDetailsServiceExt userDetailsService;
    @Autowired
    private MapperService mapperService;

    @Override
    public ShoppingListDTO getShoppingList(Long id) {
        return mapperService.shoppingListToDTO(shoppingListRepository.getOne(id));
    }

    @Override
    public ShoppingListDTO addProductItemToShoppingList(ProductItemDTO newProductItemDTO, Long shoppingListId) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isPresent()) {
            ShoppingList updatedShoppingList = optionalShoppingList.get();
            List<ProductItem> updatedProductsList = updatedShoppingList.getProductsList();
            updatedProductsList.add(productItemService.addProductItem(newProductItemDTO));
            updatedShoppingList.setProductsList(updatedProductsList);
            updatedShoppingList = shoppingListRepository.save(updatedShoppingList);
            ShoppingListDTO updatedShoppingListDTO = mapperService.shoppingListToDTO(updatedShoppingList);
            return updatedShoppingListDTO;
        } else throw new EntityExistsException("ShoppingList with id: " + shoppingListId + " doesn't exist!");
    }

    @Override
    public ShoppingListDTO updateProductItemInShoppingList(ProductItemDTO updatedProductItem, Long shoppingListId) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isPresent()) {
            productItemService.updateProductItem(mapperService.productItemToEntity(updatedProductItem));
            return mapperService.shoppingListToDTO(optionalShoppingList.get());
        } else throw new EntityExistsException("ShoppingList with id: " + shoppingListId + " doesn't exist!");
    }

    @Override
    public void removeProductItemFromList(ProductItemDTO removedProductItem, Long shoppingListId) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isPresent()) {
            ShoppingList updatedShoppingList = optionalShoppingList.get();
            List<ProductItem> updatedProductsList = updatedShoppingList.getProductsList();
            updatedProductsList.remove(mapperService.productItemToEntity(removedProductItem));
            updatedShoppingList.setProductsList(updatedProductsList);
            shoppingListRepository.save(updatedShoppingList);
            productItemService.removeProductItem(removedProductItem.getId());
        }
    }

    @Override
    public List<ShoppingListDTO> getShoppingListsByBuyerId(Long buyerId) {
        return mapperService.shoppingListToDTO(shoppingListRepository.findByBuyerId(buyerId));
    }

    @Override
    public List<ShoppingListDTO> getAllShoppingLists() {
        return mapperService.shoppingListToDTO(shoppingListRepository.findAll());
    }

    @Override
    public void deleteShoppingList(Long shoppingListId) {
        shoppingListRepository.deleteById(shoppingListId);
    }

    @Override
    public ShoppingListDTO addShoppingList(ShoppingListDTO shoppingListDTO) {
        ShoppingList newShoppingList = new ShoppingList();
        newShoppingList.setBuyerId(userDetailsService.getUserDTO().getUserId());
        newShoppingList.setShopName(shoppingListDTO.getShopName());
        return mapperService.shoppingListToDTO(shoppingListRepository.save(newShoppingList));
    }

    @Override
    public ShoppingListDTO updateShoppingList(ShoppingListDTO shoppingListDTO) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListDTO.getId());
        if (optionalShoppingList.isPresent()) {
            ShoppingList updatedShoppingList = optionalShoppingList.get();
            updatedShoppingList.setShopName(shoppingListDTO.getShopName());
            return mapperService.shoppingListToDTO(shoppingListRepository.save(updatedShoppingList));
        } else throw new EntityExistsException("ShoppingList with id: " + shoppingListDTO.getId() + " doesn't exist!");
    }

}










