package com.example.ecommercewebapp.domain.platform.product.impl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
   //List<Product> findAllByCategory_CategoryId(int categoryId);
}
