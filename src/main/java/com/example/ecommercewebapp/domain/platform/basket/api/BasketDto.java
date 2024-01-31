package com.example.ecommercewebapp.domain.platform.basket.api;

import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BasketDto {
    private String id;
    private Date created;
    private Date modified;
    private Double totalAmount;
    private Integer status;
    private UserDto user;
    private List<BasketProductDto> products;
}
