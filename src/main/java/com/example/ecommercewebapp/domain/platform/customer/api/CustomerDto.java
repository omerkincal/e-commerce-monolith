package com.example.ecommercewebapp.domain.platform.customer.api;

import com.example.ecommercewebapp.domain.platform.basket.api.BasketDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerDto {
    private String id;
    private Date created;
    private Date modified;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String password;
    private List<BasketDto> basketList;
}
