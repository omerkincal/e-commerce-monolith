package com.example.ecommercewebapp.domain.platform.customer.impl;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends AbstractEntity {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String password;
}
