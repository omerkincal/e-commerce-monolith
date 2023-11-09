package com.example.ecommercewebapp.repository;

import com.example.ecommercewebapp.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
