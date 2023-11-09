package com.example.ecommercewebapp.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int basketItemId;
    private double basketItemAmount;
    private int count;
    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "basketId")
    private Basket basket;
}
