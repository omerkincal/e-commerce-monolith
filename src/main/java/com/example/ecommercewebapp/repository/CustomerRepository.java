package com.example.ecommercewebapp.repository;


import com.example.ecommercewebapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}
