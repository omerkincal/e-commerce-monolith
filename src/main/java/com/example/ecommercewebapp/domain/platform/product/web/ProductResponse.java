package com.example.ecommercewebapp.domain.platform.product.web;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductResponse {
    private String id;
    private Date created;
    private Date modified;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private CategoryDto category;
}
