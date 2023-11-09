package com.example.ecommercewebapp.service;

import com.example.ecommercewebapp.dto.ProductDto;
import com.example.ecommercewebapp.dto.ShopAdminDto;
import com.example.ecommercewebapp.entity.ShopAdmin;
import com.example.ecommercewebapp.response.ProductResponse;

import java.util.List;

public interface ShopAdminService {
    ShopAdminDto save(ShopAdminDto shopAdminDto);
    ShopAdminDto getShopAdmin(String shopAdminId);
    ShopAdminDto update(ShopAdminDto shopAdminDto, String id);
    void delete(String id);
    List<ShopAdminDto> getAllShopAdmin();

    ShopAdmin getShopAdminEntity(String id);
    ShopAdmin toEntity(ShopAdminDto shopAdminDto);
    ShopAdminDto toDto(ShopAdmin shopAdmin);
}
