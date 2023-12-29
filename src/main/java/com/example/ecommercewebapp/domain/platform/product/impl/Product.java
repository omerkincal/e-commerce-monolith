package com.example.ecommercewebapp.domain.platform.product.impl;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = Product.TABLE)
@AllArgsConstructor
@NoArgsConstructor
public class Product extends AbstractEntity {
    public static final String TABLE = "product";
    public static final String COL_NAME = "name";
    public static final String COL_STOCK = "stock";
    public static final String COL_PRICE = "price";
    public static final String COL_CATEGORY_ID = "category_id";
    public static final String COL_DESCRIPTION = "description";

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_DESCRIPTION)
    private String description;

    @Column(name = COL_STOCK)
    private Integer stock;

    @Column(name = COL_PRICE)
    private Double price;

    @Column(name = COL_CATEGORY_ID)
    private String categoryId;
}
