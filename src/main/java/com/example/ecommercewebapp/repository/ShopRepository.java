package com.example.ecommercewebapp.repository;


import com.example.ecommercewebapp.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop,Integer> {
}
