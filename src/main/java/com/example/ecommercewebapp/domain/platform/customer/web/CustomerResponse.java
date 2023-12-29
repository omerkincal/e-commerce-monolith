package com.example.ecommercewebapp.domain.platform.customer.web;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CustomerResponse {
    private String id;
    private Date created;
    private Date modified;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String password;
}
