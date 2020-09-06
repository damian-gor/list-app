package com.damgor.listapp.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingListDTO {
    private Long id;
    private UserDTO buyer;
    private List<ProductItemDTO> productsList;
    private List<UserDTO> participantsList;
    private String shopName;
}
