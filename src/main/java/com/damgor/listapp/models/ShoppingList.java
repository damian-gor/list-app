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
    @ElementCollection
    private List<Long> participantsIdsList;
    //    ToDo zrobic z tego obiekt sklepu
    private String shopName;
//    ToDo data zakupow

    public ShoppingList(Long buyerId, List<ProductItem> productsList, List<Long> participantsIdsList, String shopName) {
        this.buyerId = buyerId;
        this.productsList = productsList;
        this.participantsIdsList = participantsIdsList;
        this.shopName = shopName;
    }
}
