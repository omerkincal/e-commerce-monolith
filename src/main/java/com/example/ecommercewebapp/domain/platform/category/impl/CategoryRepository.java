package com.example.ecommercewebapp.domain.platform.category.impl;

import com.example.ecommercewebapp.domain.platform.category.impl.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
