package com.example.ecommercewebapp.service.impl;

import com.example.ecommercewebapp.dto.ShopAdminDto;
import com.example.ecommercewebapp.entity.ShopAdmin;
import com.example.ecommercewebapp.repository.ShopAdminRepository;
import com.example.ecommercewebapp.service.ShopAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShopAdminServiceImpl implements ShopAdminService {

    private final ShopAdminRepository repository;

    @Override
    public ShopAdminDto save(ShopAdminDto shopAdminDto) {
        ShopAdmin shopAdmin = toEntity(shopAdminDto);
        shopAdmin = repository.save(shopAdmin);
        return toDto(shopAdmin);
    }

    @Override
    public ShopAdminDto getShopAdmin(String shopAdminId) {
        return toDto(repository.findById(Integer.valueOf(shopAdminId)).get());
    }

    @Override
    public ShopAdminDto update(ShopAdminDto shopAdminDto, String id) {
        ShopAdmin shopAdmin = getShopAdminEntity(id);
        shopAdmin.setName(shopAdminDto.getName());
        shopAdmin.setAddress(shopAdminDto.getAddress());
        shopAdmin.setPhone(shopAdminDto.getPhone());
        shopAdmin.setSurname(shopAdminDto.getSurname());
        shopAdmin.setPassword(shopAdminDto.getPassword());
        shopAdmin.setEmail(shopAdminDto.getEmail());
        shopAdmin = repository.save(shopAdmin);
        return toDto(shopAdmin);
    }

    @Override
    public void delete(String id) {
        ShopAdmin shopAdmin = getShopAdminEntity(id);
        repository.delete(shopAdmin);

    }

    @Override
    public List<ShopAdminDto> getAllShopAdmin() {
        return repository.findAll()
                .stream()
                .map(shopAdmin -> toDto(shopAdmin))
                .collect(Collectors.toList());
    }


    @Override
    public ShopAdmin toEntity(ShopAdminDto dto){
        ShopAdmin shopAdmin = new ShopAdmin();
        shopAdmin.setSurname(dto.getSurname());
        shopAdmin.setName(dto.getName());
        shopAdmin.setPhone(dto.getPhone());
        shopAdmin.setAddress(dto.getAddress());
        shopAdmin.setEmail(dto.getEmail());
        shopAdmin.setPassword(dto.getPassword());
        return shopAdmin;
    }


    @Override
    public ShopAdminDto toDto(ShopAdmin shopAdmin){
        return ShopAdminDto.builder()
                .id(shopAdmin.getShopAdminId())
                .name(shopAdmin.getName())
                .surname(shopAdmin.getSurname())
                .email(shopAdmin.getEmail())
                .phone(shopAdmin.getPhone())
                .password(shopAdmin.getPassword())
                .address(shopAdmin.getAddress())
                .build();
    }


    @Override
    public ShopAdmin getShopAdminEntity(String id){
        return repository.findById(Integer.parseInt(id)).get();
    }
}
