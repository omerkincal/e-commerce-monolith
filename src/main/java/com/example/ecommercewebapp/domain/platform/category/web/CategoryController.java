package com.example.ecommercewebapp.domain.platform.category.web;

import com.example.ecommercewebapp.domain.platform.category.api.CategoryMapper;
import com.example.ecommercewebapp.domain.platform.category.api.CategoryService;
import com.example.ecommercewebapp.library.rest.BaseController;
import com.example.ecommercewebapp.library.rest.PageResponse;
import com.example.ecommercewebapp.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
public class CategoryController extends BaseController {

    private final CategoryService service;

    @PostMapping
    public Response<CategoryResponse> createCategory(@RequestBody CategoryRequest request){
        return respond(CategoryMapper.toResponse(service.createCategory(CategoryMapper.toDto(request))));
    }

    @GetMapping("{id}")
    public Response<CategoryResponse> getById(@PathVariable String id) {
        return respond(CategoryMapper.toResponse(service.getById(id)));
    }

    @PutMapping("{id}")
    public Response<CategoryResponse> update(@PathVariable String id,
                                   @RequestBody CategoryRequest categoryRequest){
        return respond(CategoryMapper.toResponse(service.update(CategoryMapper.toDto(categoryRequest), id)));
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable String id){
        service.delete(id);
        return "deleted";
    }

    @GetMapping
    public Response<PageResponse<CategoryResponse>> getAllCategories(Pageable pageable) {
        return respond(CategoryMapper.toPageResponse(service.getAllCategory(pageable)));
    }



}
