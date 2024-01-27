package com.example.ecommercewebapp.domain.platform.category.web;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryRequest {
    private String name;
    private String description;


    //bunu data anotasyonunda tek parametre yollamada hata aldığın için yaptın
    //public CategoryRequest(@JsonProperty("categoryName") String categoryName){
    //  this.categoryName=categoryName;
    //}
}
