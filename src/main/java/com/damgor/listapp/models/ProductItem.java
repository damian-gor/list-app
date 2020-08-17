package com.damgor.listapp.models;

import com.damgor.listapp.models.enums.ProductCategory;
import com.damgor.listapp.models.enums.ProductStatus;
import com.damgor.listapp.models.enums.ProductUnit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Embeddable
public class ProductItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double quantity;
    @Enumerated(EnumType.STRING)
    private ProductUnit unit;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private Long ownerId;
    private ProductStatus productStatus = ProductStatus.IN_PROGRESS;
    private Long sourceProductId;

    public ProductItem(Double quantity, ProductUnit unit, String name, ProductCategory category, Long ownerId) {
        this.quantity = quantity;
        this.unit = unit;
        this.name = name;
        this.category = category;
        this.ownerId = ownerId;
    }
    public ProductItem(Double quantity, ProductUnit unit, String name, ProductCategory category, Long ownerId, ProductStatus productStatus) {
        this.quantity = quantity;
        this.unit = unit;
        this.name = name;
        this.category = category;
        this.ownerId = ownerId;
        this.productStatus =  productStatus;
    }
}
