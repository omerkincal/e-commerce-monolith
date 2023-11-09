package com.example.ecommercewebapp.response;

import com.example.ecommercewebapp.dto.CategoryDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShopResponse {
    private final int shopId;
    private final String shopName;
    private final String address;
    private final int shopAdminId;
    //private final List<CategoryDto> categoryList;
}
