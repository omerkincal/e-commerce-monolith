package com.example.ecommercewebapp.service;

import com.example.ecommercewebapp.dto.ShopDto;
import com.example.ecommercewebapp.entity.Shop;

import java.util.List;

public interface ShopService {
    ShopDto save(ShopDto shopDto);
    ShopDto update(ShopDto shopDto, String id);
    ShopDto getShop(String id);
    void delete(String id);
    List<ShopDto> getAllShop();
    Shop toEntity(ShopDto shopDto);
    Shop getShopEntity(String id);
    ShopDto toDto(Shop shop);
}
