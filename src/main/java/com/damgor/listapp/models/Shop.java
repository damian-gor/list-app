package com.damgor.listapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Shop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @URL
    private String promotionUrl;
    @OneToMany(mappedBy = "shop", cascade=CascadeType.REMOVE) // nazwa parametru w klasie ShoppingList
    @JsonIgnoreProperties("shop")
    private List<ShoppingList> shoppingLists;

    public Shop(String name, String promotionUrl) {
        this.name = name;
        this.promotionUrl = promotionUrl;
    }

    public Shop(Long id, String name, String promotionUrl) {
        this.id = id;
        this.name = name;
        this.promotionUrl = promotionUrl;
    }
}
