package com.example.ecommercewebapp.domain.platform.customer.web;

import com.example.ecommercewebapp.domain.platform.customer.api.CustomerDto;
import com.example.ecommercewebapp.domain.platform.customer.api.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    @PostMapping
    public CustomerResponse save(@RequestBody CustomerRequest customerRequest){
        return toResponse(service.save(customerRequest.toDto()));
    }

    @GetMapping("{id}")
    public CustomerResponse getCustomerById(@PathVariable String id){
        return toResponse(service.getCustomer(id));
    }

    @GetMapping
    public List<CustomerResponse> getCustomerById(){
        return service.getAllCustomer()
                .stream()
                .map(customerDto -> toResponse(customerDto))
                .collect(Collectors.toList());
    }

    @PutMapping("{id}")
    public CustomerResponse update(@PathVariable String id,
                                   @RequestBody CustomerRequest customerRequest){
        return toResponse(service.update(customerRequest.toDto(),id));
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id){
        service.delete(id);
        return "Başarıyla Silindi";
    }


    public CustomerResponse toResponse(CustomerDto customerDto){
        return CustomerResponse.builder()
                .customerId(customerDto.getCustomerId())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .address(customerDto.getAddress())
                .password(customerDto.getPassword())
                .email(customerDto.getEmail())
                .phone(customerDto.getPhone())
                .message("Successfull")
                .code(200)
                .build();
    }


}
