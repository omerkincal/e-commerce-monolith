package com.example.ecommercewebapp.request;

import com.example.ecommercewebapp.dto.ShopDto;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ShopRequest {
    private final String shopName;
    private final String address;
    private final int shopAdminId;


    public ShopDto toDto(){
        return ShopDto.builder()
                .shopName(this.shopName)
                .address(this.address)
                .shopAdminId(this.shopAdminId)
                .build();
    }
}
