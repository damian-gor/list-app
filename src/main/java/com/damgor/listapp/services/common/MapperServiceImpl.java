package com.damgor.listapp.services.common;

import com.damgor.listapp.models.DTOs.ProductItemDTO;
import com.damgor.listapp.models.DTOs.ShoppingListDTO;
import com.damgor.listapp.models.ProductItem;
import com.damgor.listapp.models.ShoppingList;
import com.damgor.listapp.security.services.UserDetailsServiceExt;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MapperServiceImpl implements MapperService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserDetailsServiceExt userDetailsService;

    public ProductItemDTO productItemToDto(ProductItem productItem) {
        ProductItemDTO productItemDTO = modelMapper.map(productItem, ProductItemDTO.class);
        if (productItem.getOwnerId() != null)
            productItemDTO.setAuthor(userDetailsService.getUserDTObyId(productItem.getOwnerId()));
        return productItemDTO;
    }

    public ProductItem productItemToEntity(ProductItemDTO productItemDTO) {
        ProductItem productItem = modelMapper.map(productItemDTO, ProductItem.class);
        if (productItemDTO.getAuthor() != null)
            productItem.setOwnerId(productItemDTO.getAuthor().getUserId());
        return productItem;
    }

    public List<ShoppingListDTO> shoppingListToDTO(List<ShoppingList> shoppingLists) {
        List<ShoppingListDTO> shoppingListDTOList = new ArrayList<>();
        shoppingLists.forEach(list -> {
            ShoppingListDTO shoppingListDTO = modelMapper.map(list, ShoppingListDTO.class);
            shoppingListDTO.setBuyer(userDetailsService.getUserDTObyId(list.getBuyerId()));
            if (list.getProductsList() != null)
                shoppingListDTO.setProductsList(list.getProductsList()
                        .stream()
                        .map(this::productItemToDto)
                        .collect(Collectors.toList()));
            // ToDo Shop + Participants
            shoppingListDTOList.add(shoppingListDTO);
        });
        return shoppingListDTOList;
    }

    public ShoppingListDTO shoppingListToDTO(ShoppingList shoppingList) {
        return shoppingListToDTO(Collections.singletonList(shoppingList)).get(0);
    }

    @Override
    public ShoppingList shoppingListToEntity(ShoppingListDTO shoppingListDTO) {
        ShoppingList shoppingList = modelMapper.map(shoppingListDTO, ShoppingList.class);
        shoppingList.setBuyerId(shoppingListDTO.getBuyer().getUserId());
        if (shoppingListDTO.getProductsList() != null)
            shoppingList.setProductsList(shoppingListDTO.getProductsList()
                    .stream()
                    .map(this::productItemToEntity)
                    .collect(Collectors.toList()));
        return shoppingList;
    }
}
