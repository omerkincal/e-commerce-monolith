package com.example.ecommercewebapp.repository;

import com.example.ecommercewebapp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
   //List<Product> findAllByCategory_CategoryId(int categoryId);
}
