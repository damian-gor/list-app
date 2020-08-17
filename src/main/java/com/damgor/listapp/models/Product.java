package com.damgor.listapp.models;

import com.damgor.listapp.models.enums.ProductCategory;
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
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    @Enumerated(EnumType.STRING)
    private ProductUnit unit;

    public Product(String name, ProductCategory category) {
        this.name = name;
        this.category = category;
    }
}
