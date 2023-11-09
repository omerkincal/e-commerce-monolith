package com.example.ecommercewebapp.dto;

import com.example.ecommercewebapp.entity.Category;
import com.example.ecommercewebapp.entity.ShopAdmin;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShopDto {
    private final int shopId;
    private final String shopName;
    private final String address;
    private final int shopAdminId;
}
