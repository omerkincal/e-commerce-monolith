package com.example.ecommercewebapp.domain.platform.product.api;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductDto {
    private String id;
    private Date created;
    private Date modified;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private CategoryDto category;
}