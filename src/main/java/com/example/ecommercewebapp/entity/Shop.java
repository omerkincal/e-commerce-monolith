package com.example.ecommercewebapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int shopId;
    private String shopName;
    private String address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopAdminId")
    private ShopAdmin shopAdmin;
    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
    private  List<Category> categoryList;
}