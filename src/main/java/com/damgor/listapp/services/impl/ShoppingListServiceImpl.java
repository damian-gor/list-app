package com.damgor.listapp.services.impl;

import com.damgor.listapp.models.DTOs.ProductItemDTO;
import com.damgor.listapp.models.DTOs.ShoppingListDTO;
import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.models.Shop;
import com.damgor.listapp.models.ShoppingList;
import com.damgor.listapp.models.User;
import com.damgor.listapp.repositories.ShoppingListRepository;
import com.damgor.listapp.security.services.UserDetailsServiceExt;
import com.damgor.listapp.services.ImageService;
import com.damgor.listapp.services.ProductItemService;
import com.damgor.listapp.services.ShopService;
import com.damgor.listapp.services.ShoppingListService;
import com.damgor.listapp.services.common.MapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.*;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    @Autowired
    private ShoppingListRepository shoppingListRepository;
    @Autowired
    private ProductItemService productItemService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private UserDetailsServiceExt userDetailsService;
    @Autowired
    private MapperService mapperService;
    @Autowired
    private ImageService imageService;

    @Override
    public ShoppingListDTO getShoppingList(Long id) {
        ShoppingListDTO shoppingListDTO = mapperService.shoppingListToDTO(shoppingListRepository.getOne(id));
        setHasImageFlagToListProductItems(shoppingListDTO);
        return shoppingListDTO;
    }

    private void setHasImageFlagToListProductItems(ShoppingListDTO shoppingListDTO) {
        List<ProductItemDTO> productItemDTOList = shoppingListDTO.getProductsList();
        if (!productItemDTOList.isEmpty()) {
            List<Long> productItemsIds = new ArrayList<>();
            productItemDTOList.forEach(p -> productItemsIds.add(p.getId()));
            List<Long> productItemsWithImagesIds = imageService.getIdsOfProductItemsWithImages(productItemsIds);
            if (!productItemsWithImagesIds.isEmpty())
                productItemDTOList.
                        stream()
                        .filter(productItemDTO -> productItemsWithImagesIds.contains(productItemDTO.getId()))
                        .forEach(productItemDTO -> productItemDTO.setHasImage(true));
        }
    }

    @Override
    public ShoppingListDTO addProductItemToShoppingList(
            ProductItemDTO newProductItemDTO, Long shoppingListId) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isPresent()) {
            ShoppingList updatedShoppingList = optionalShoppingList.get();
            List<ProductItem> updatedProductsList = updatedShoppingList.getProductsList();
            updatedProductsList.add(productItemService.addProductItem(newProductItemDTO));
            updatedShoppingList.setProductsList(updatedProductsList);
            updatedShoppingList = shoppingListRepository.save(updatedShoppingList);
            ShoppingListDTO updatedShoppingListDTO = mapperService.shoppingListToDTO(updatedShoppingList);
            setHasImageFlagToListProductItems(updatedShoppingListDTO);
            return updatedShoppingListDTO;
        } else
            throw new EntityExistsException(
                    "ShoppingList with id: " + shoppingListId + " doesn't exist!");
    }

    @Override
    public ShoppingListDTO updateProductItemInShoppingList(
            ProductItemDTO updatedProductItem, Long shoppingListId) {
        Optional<ShoppingList> optionalShoppingList = shoppingListRepository.findById(shoppingListId);
        if (optionalShoppingList.isPresent()) {
            productItemService.updateProductItem(mapperService.productItemToEntity(updatedProductItem));
            return mapperService.shoppingListToDTO(optionalShoppingList.get());
        } else
            throw new EntityExistsException(
                    "ShoppingList with id: " + shoppingListId + " doesn't exist!");
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
    public List<ShoppingListDTO> getAllShoppingListsConnectedWithUser() {
        Long loggedUserId = userDetailsService.getUserDTO().getUserId();
        User user = userDetailsService.getUserById(loggedUserId);
        List<ShoppingList> usersShoppingLists2 = shoppingListRepository.findByParticipantsListIsContainingOrBuyerId(user, loggedUserId);
        return mapperService.shoppingListToDTO(usersShoppingLists2);
    }


    @Override
    public List<ShoppingListDTO> getUserShoppingLists() {
        List<ShoppingList> userShoppingLists = new ArrayList<>();
        Long loggedUserId = userDetailsService.getUserDTO().getUserId();
        shoppingListRepository.findAll().forEach(shoppingList -> {
            if (shoppingList.getBuyerId() == loggedUserId) {
                userShoppingLists.add(shoppingList);
            } else {
                List<Long> participantsIds = new ArrayList<>();
                shoppingList.getParticipantsList().forEach(participant -> {
                    participantsIds.add(participant.getId());
                });
                if (participantsIds.contains(loggedUserId)) userShoppingLists.add(shoppingList);
            }
        });
        return mapperService.shoppingListToDTO(userShoppingLists);
    }

    @Override
    public void deleteShoppingList(Long shoppingListId) {
        shoppingListRepository.deleteById(shoppingListId);
    }

    @Override
    public ShoppingListDTO addShoppingList(ShoppingListDTO shoppingListDTO) {
        ShoppingList newShoppingList = new ShoppingList();
        newShoppingList.setBuyerId(userDetailsService.getUserDTO().getUserId());
        newShoppingList.setShop(shopService.getExistingShopByNameOrCreateNewOne(shoppingListDTO.getShopName()));
        if (!shoppingListDTO.getParticipantsList().isEmpty()) {
            List<User> participantsEntities =
                    new ArrayList<>(userDetailsService.userDTOsListToUsersSet(shoppingListDTO.getParticipantsList()));
            newShoppingList.setParticipantsList(participantsEntities);
        }
        Shop shopToVerify = new Shop(shoppingListDTO.getShopName(), shoppingListDTO.getShopPromotionUrl());
        newShoppingList.setShop(shopService.addShopIfNotExists(shopToVerify));

        return mapperService.shoppingListToDTO(shoppingListRepository.save(newShoppingList));
    }

    @Override
    public ShoppingListDTO updateShoppingList(ShoppingListDTO shoppingListDTO) {
        Optional<ShoppingList> optionalShoppingList =
                shoppingListRepository.findById(shoppingListDTO.getId());
        if (optionalShoppingList.isPresent()) {
            ShoppingList updatedShoppingList = optionalShoppingList.get();
            updatedShoppingList.setShop(shopService.getExistingShopByNameOrCreateNewOne(shoppingListDTO.getShopName()));
            if (!shoppingListDTO.getParticipantsList().isEmpty()) {
                List<User> participantsEntities =
                        new ArrayList<>(userDetailsService.userDTOsListToUsersSet(shoppingListDTO.getParticipantsList()));
                updatedShoppingList.setParticipantsList(participantsEntities);
            } else updatedShoppingList.setParticipantsList(Collections.emptyList());
            return mapperService.shoppingListToDTO(shoppingListRepository.save(updatedShoppingList));
        } else
            throw new EntityExistsException(
                    "ShoppingList with id: " + shoppingListDTO.getId() + " doesn't exist!");
    }

}
