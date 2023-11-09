package com.example.ecommercewebapp.request;

import com.example.ecommercewebapp.dto.BasketDto;
import com.example.ecommercewebapp.dto.BasketItemDto;
import com.example.ecommercewebapp.dto.CustomerDto;
import com.example.ecommercewebapp.dto.ProductDto;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AddBasketRequest {
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
