package com.example.ecommercewebapp.domain.platform.category.web;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryRequest {

    private final String categoryName;
    private final int shopId;

    //bunu data anotasyonunda tek parametre yollamada hata aldığın için yaptın
    //public CategoryRequest(@JsonProperty("categoryName") String categoryName){
      //  this.categoryName=categoryName;
    //}

    public CategoryDto toDto(){
        return CategoryDto.builder()
                .categoryName(this.categoryName)
                .shopId(this.shopId)
                .build();
    }
}
