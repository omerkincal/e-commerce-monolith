package com.example.ecommercewebapp.domain.platform.customer.impl;


import com.example.ecommercewebapp.domain.platform.customer.impl.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
