package com.example.ecommercewebapp.dto;

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
