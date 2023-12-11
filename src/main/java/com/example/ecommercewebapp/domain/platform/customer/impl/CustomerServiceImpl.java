package com.example.ecommercewebapp.domain.platform.customer.impl;

import com.example.ecommercewebapp.domain.platform.customer.api.CustomerDto;
import com.example.ecommercewebapp.domain.platform.customer.api.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;


    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = toEntity(customerDto);
        customer = repository.save(customer);
        return toDto(customer);
    }

    @Override
    public CustomerDto update(CustomerDto customerDto, String customerId) {
        Customer customer = repository.findById(Integer.parseInt(customerId)).get();
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());
        customer.setPassword(customerDto.getPassword());
        customer.setEmail(customerDto.getEmail());
        customer = repository.save(customer);
        return toDto(customer);
    }

    @Override
    public CustomerDto getCustomer(String id) {
        return toDto(getCustomerEntity(id));
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        return repository.findAll()
                .stream()
                .map(customer -> toDto(customer))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        repository.delete(getCustomerEntity(id));
    }


    @Override
    public CustomerDto toDto(Customer customer){
        return CustomerDto.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .password(customer.getPassword())
                .address(customer.getAddress())
                .build();
    }

    @Override
    public Customer toEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setPassword(customerDto.getPassword());
        return customer;
    }

    @Override
    public Customer getCustomerEntity(String id){
        return repository.findById(Integer.parseInt(id)).get();
    }
}
