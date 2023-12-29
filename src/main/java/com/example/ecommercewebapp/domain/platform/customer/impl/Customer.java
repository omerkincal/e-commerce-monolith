package com.example.ecommercewebapp.domain.platform.customer.impl;

import com.example.ecommercewebapp.library.rest.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = Customer.TABLE)
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends AbstractEntity {
    public static final String TABLE = "customer";
    public static final String COL_NAME = "name";
    public static final String COL_SURNAME = "surname";
    public static final String COL_EMAIL = "email";
    public static final String COL_PHONE = "phone";
    public static final String COL_ADDRESS = "address";
    public static final String COL_PASSWORD = "password";

    @Column(name = COL_NAME)
    private String name;

    @Column(name = COL_SURNAME)
    private String surname;

    @Column(name = COL_EMAIL)
    private String email;

    @Column(name = COL_PHONE)
    private String phone;

    @Column(name = COL_ADDRESS)
    private String address;

    @Column(name = COL_PASSWORD)
    private String password;
}
