package com.example.ecommercewebapp.domain.platform.category.web;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryDto;
import com.example.ecommercewebapp.domain.platform.category.api.CategoryService;
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
                .id(categoryDto.getId())
                .created(categoryDto.getCreated())
                .modified(categoryDto.getModified())
                .name(categoryDto.getName())
                .build();
    }
}
