package com.example.ecommercewebapp.response;

import com.example.ecommercewebapp.dto.BasketItemDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BasketResponse {
    private final int basketId;
    private final double totalAmount;
    private final int status;
    private final int customerId;
    private final List<BasketItemDto> basketItemList;
}
