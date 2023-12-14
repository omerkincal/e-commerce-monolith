package com.example.ecommercewebapp.domain.platform.customer.api;

import com.example.ecommercewebapp.domain.platform.customer.impl.Customer;

import java.util.List;

public interface CustomerService {
    CustomerDto save(CustomerDto customerDto);
    CustomerDto update(CustomerDto customerDto, String customerId);
    CustomerDto getById(String id);
    List<CustomerDto> getAllCustomer();
    void delete(String id);
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customerDto);
    Customer getCustomerEntity(String id);
}
