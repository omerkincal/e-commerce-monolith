package com.example.ecommercewebapp.domain.platform.category.api;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CategoryDto {
    private String id;
    private Date created;
    private Date modified;
    private String name;
}
