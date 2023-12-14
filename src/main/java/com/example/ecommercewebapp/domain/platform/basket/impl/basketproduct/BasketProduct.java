package com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = BasketProduct.TABLE)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BasketProduct extends AbstractEntity {
    public static final String TABLE = "basket_product";
    public static final String COL_PRODUCT_ID = "product_id";
    public static final String COL_QUANTITY = "quantity";
    public static final String COL_BASKET_ID = "basket_id";
    public static final String COL_BASKET_PRODUCT_AMOUNT = "basket_id";

    @Column(name = COL_BASKET_PRODUCT_AMOUNT)
    private Double basketProductAmount;

    @Column(name = COL_QUANTITY)
    private Integer quantity;

    @Column(name = COL_PRODUCT_ID)
    private String productId;

    @Column(name = COL_BASKET_ID)
    private String basketId;
}
