package com.damgor.listapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ShoppingList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long buyerId;
    @ElementCollection
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ProductItem> productsList;

    @ManyToMany
    private List<User> participantsList;

    @OneToOne
    private Shop shop;

    public ShoppingList(Long buyerId, List<ProductItem> productsList, Shop shop, List<User> participantsList) {
        this.buyerId = buyerId;
        this.productsList = productsList;
        this.participantsList = participantsList;
        this.shop = shop;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", buyerId=" + buyerId +
                ", productsList=" + productsList +
                ", participantsList=" + "" +
                ", shop=" + shop +
                '}';
    }
}
