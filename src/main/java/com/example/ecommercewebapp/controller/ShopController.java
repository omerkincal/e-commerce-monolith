package com.example.ecommercewebapp.controller;

import com.example.ecommercewebapp.dto.ShopDto;
import com.example.ecommercewebapp.request.ShopRequest;
import com.example.ecommercewebapp.response.ShopResponse;
import com.example.ecommercewebapp.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("shops")
@RequiredArgsConstructor
public class ShopController {
    private final ShopService service;

    @PostMapping
    public ShopResponse save(@RequestBody ShopRequest shopRequest){
        return toResponse(service.save(shopRequest.toDto()));
    }

    @GetMapping("{id}")
    public ShopResponse getShopAdminById(@PathVariable String id){
        return toResponse(service.getShop(id));
    }

    @PutMapping("{id}")
    public ShopResponse update(@PathVariable String id,
                                    @RequestBody ShopRequest shopRequest){
        return toResponse(service.update(shopRequest.toDto(),id));
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id){
        service.delete(id);
        return "Başarıyla Silindi";
    }


    @GetMapping
    public List<ShopResponse> getAllShopAdmins(){
        return service.getAllShop()
                .stream()
                .map(shopDto -> toResponse(shopDto))
                .collect(Collectors.toList());
    }

    public ShopResponse toResponse(ShopDto shopDto){
        return ShopResponse.builder()
                .shopName(shopDto.getShopName())
                .shopId(shopDto.getShopId())
                .address(shopDto.getAddress())
                .shopAdminId(shopDto.getShopAdminId())
                //.categoryList(shopDto.getCategoryList())
                .build();
    }
}
