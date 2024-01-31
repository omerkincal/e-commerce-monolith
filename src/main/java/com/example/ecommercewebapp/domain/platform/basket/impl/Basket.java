package com.example.ecommercewebapp.domain.platform.basket.impl;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = Basket.TABLE)
@AllArgsConstructor
@NoArgsConstructor
public class Basket extends AbstractEntity {
    public static final String TABLE = "basket";
    public static final String COL_TOTAL_AMOUNT = "total_amount";
    public static final String COL_STATUS = "status";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_PRODUCTS = "products";


    @Column(name = COL_TOTAL_AMOUNT)
    private Double totalAmount;

    @Column(name = COL_STATUS)
    private Integer status;

    @Column(name = COL_USER_ID)
    private String userId;
}
