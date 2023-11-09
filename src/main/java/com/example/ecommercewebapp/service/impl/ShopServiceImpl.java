package com.example.ecommercewebapp.service.impl;

import com.example.ecommercewebapp.dto.ShopDto;
import com.example.ecommercewebapp.entity.Shop;
import com.example.ecommercewebapp.repository.ShopRepository;
import com.example.ecommercewebapp.service.CategoryService;
import com.example.ecommercewebapp.service.ShopAdminService;
import com.example.ecommercewebapp.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopRepository repository;
    private final ShopAdminServiceImpl shopAdminService;



    @Override
    public ShopDto save(ShopDto shopDto) {
        Shop shop = toEntity(shopDto);
        shop = repository.save(shop);
        return toDto(shop);
    }

    @Override
    public ShopDto update(ShopDto shopDto, String id) {
        Shop shop = repository.findById(Integer.parseInt(id)).get();
        shop.setShopName(shopDto.getShopName());
        shop.setShopAdmin(shopAdminService.getShopAdminEntity(String.valueOf(shopDto.getShopAdminId())));
        shop.setAddress(shopDto.getAddress());
        shop = repository.save(shop);
        return toDto(shop);
    }

    @Override
    public ShopDto getShop(String id) {
        return toDto(getShopEntity(id));
    }

    @Override
    public void delete(String id) {
        Shop shop = getShopEntity(id);
        repository.delete(shop);
    }

    @Override
    public List<ShopDto> getAllShop() {
        return repository.findAll()
                .stream()
                .map(shop -> toDto(shop))
                .collect(Collectors.toList());
    }

    @Override
    public ShopDto toDto(Shop shop){
        return ShopDto.builder()
                .shopId(shop.getShopId())
                .address(shop.getAddress())
                .shopName(shop.getShopName())
                .shopAdminId(shop.getShopAdmin().getShopAdminId())
                .build();
    }

    @Override
    public Shop toEntity(ShopDto shopDto){
        Shop shop = new Shop();
        shop.setAddress(shopDto.getAddress());
        shop.setShopAdmin(shopAdminService.getShopAdminEntity(String.valueOf(shopDto.getShopAdminId())));
        shop.setShopName(shopDto.getShopName());
        return shop;
    }

    @Override
    public Shop getShopEntity(String id){
        return repository.findById(Integer.parseInt(id)).get();
    }
}
