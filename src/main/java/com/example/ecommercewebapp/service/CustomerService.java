package com.example.ecommercewebapp.service;

import com.example.ecommercewebapp.dto.CustomerDto;
import com.example.ecommercewebapp.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDto save(CustomerDto customerDto);
    CustomerDto update(CustomerDto customerDto, String customerId);
    CustomerDto getCustomer(String id);
    List<CustomerDto> getAllCustomer();
    void delete(String id);
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
    Customer getCustomerEntity(String id);
}
