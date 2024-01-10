package com.example.ecommercewebapp.domain.platform.category.web;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

    private String name;
    private String description;


    //bunu data anotasyonunda tek parametre yollamada hata aldığın için yaptın
    //public CategoryRequest(@JsonProperty("categoryName") String categoryName){
    //  this.categoryName=categoryName;
    //}
}
