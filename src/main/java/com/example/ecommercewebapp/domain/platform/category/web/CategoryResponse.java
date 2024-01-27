package com.example.ecommercewebapp.domain.platform.category.web;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryResponse {
    private String id;
    private Date created;
    private Date modified;
    private String name;
    private String description;
}
