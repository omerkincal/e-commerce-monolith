package com.example.ecommercewebapp.request;

import com.example.ecommercewebapp.dto.CategoryDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
