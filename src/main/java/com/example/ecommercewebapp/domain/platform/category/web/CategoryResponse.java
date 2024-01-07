package com.example.ecommercewebapp.domain.platform.category.web;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CategoryResponse {
    private String id;
    private Date created;
    private Date modified;
    private String name;
}
