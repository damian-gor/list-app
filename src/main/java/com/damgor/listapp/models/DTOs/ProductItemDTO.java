package com.damgor.listapp.models.DTOs;

import com.damgor.listapp.models.enums.ProductCategory;
import com.damgor.listapp.models.enums.ProductStatus;
import com.damgor.listapp.models.enums.ProductUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemDTO {

    private Long id;
    private String name;
    private Double quantity;
    private ProductUnit unit;
    private ProductCategory category;
    private UserDTO author;
    private ProductStatus productStatus = ProductStatus.IN_PROGRESS;
    private Long sourceProductId;

}
