package com.example.ecommercewebapp.controller;

import com.example.ecommercewebapp.dto.CategoryDto;
import com.example.ecommercewebapp.request.CategoryRequest;
import com.example.ecommercewebapp.response.CategoryResponse;
import com.example.ecommercewebapp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest categoryRequest){
        CategoryDto categoryDto = service.save(categoryRequest.toDto());
        CategoryResponse response = toResponse(categoryDto);
        return response;
    }

    @GetMapping("{id}")
    public CategoryResponse getCustomer(@PathVariable String id){
        return toResponse(service.getCategory(id));
    }

    @PutMapping("{id}")
    public CategoryResponse update(@PathVariable String id,
                                       @RequestBody CategoryRequest categoryRequest){
        return toResponse(service.updateCategory(categoryRequest.toDto(), id));
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id){
        try {
            service.delete(id);
            return "Başarıyla silindi";
        }catch (Exception ex){
            return "Silme işleminde şu hata oldu: " + ex.getMessage();
        }
    }

    @GetMapping
    public List<CategoryResponse> getAllCategory(){
       return service.getAllCategory()
               .stream()
               .map(categoryDto -> toResponse(categoryDto))
               .collect(Collectors.toList());
    }


    public CategoryResponse toResponse(CategoryDto categoryDto){
        return CategoryResponse.builder()
                .categoryName(categoryDto.getCategoryName())
                .categoryId(categoryDto.getCategoryId())
                .shopId(categoryDto.getShopId())
                .code(200)
                .message("succesfull")
                .build();
    }
}