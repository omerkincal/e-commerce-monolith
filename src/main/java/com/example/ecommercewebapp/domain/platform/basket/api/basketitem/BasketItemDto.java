package com.example.ecommercewebapp.domain.platform.basket.api.basketitem;

import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BasketItemDto {
    public int basketItemId;
    public double basketItemAmount;
    public int count;
    public ProductDto product;
}
