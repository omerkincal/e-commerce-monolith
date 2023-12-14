package com.example.ecommercewebapp.domain.platform.basket.api.basketproduct;

import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BasketProductDto {
    private String id;
    private Date created;
    private Date modified;
    private String basketId;
    private Double basketProductAmount;
    private Integer quantity;
    private ProductDto product;
}
