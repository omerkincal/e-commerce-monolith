package com.example.ecommercewebapp.domain.platform.basket.web;

import com.example.ecommercewebapp.domain.platform.basket.api.BasketDto;
import com.example.ecommercewebapp.domain.platform.basket.api.basketitem.BasketItemDto;
import com.example.ecommercewebapp.domain.platform.customer.api.CustomerDto;
import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BasketRequest {
    private int productId;
    private  int count;
    private  int basketId;
    private  int customerId;


    public BasketDto toDto(){
        BasketItemDto dto = BasketItemDto.builder().product(new ProductDto(productId)).build();
        dto.count = count;
        List<BasketItemDto> basketItemList = new ArrayList<>();
        basketItemList.add(dto);
        return BasketDto.builder()
                .customer(CustomerDto.builder().customerId(customerId).build())
                .basketId(basketId)
                .basketItemList(basketItemList)
                .build();
    }
}
