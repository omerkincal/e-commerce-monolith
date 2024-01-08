package com.example.ecommercewebapp.domain.platform.product.web;

import com.example.ecommercewebapp.domain.platform.product.api.ProductMapper;
import com.example.ecommercewebapp.domain.platform.product.api.ProductService;
import com.example.ecommercewebapp.library.rest.BaseController;
import com.example.ecommercewebapp.library.rest.MetaResponse;
import com.example.ecommercewebapp.library.rest.PageResponse;
import com.example.ecommercewebapp.library.rest.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController extends BaseController {

    private final ProductService service;

    @GetMapping
    public Response<PageResponse<ProductResponse>> getAllProducts(Pageable pageable) {
        return respond(ProductMapper.toPageResponse(service.getAllProducts(pageable)));
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Response<ProductResponse> createProduct(@Valid @RequestBody ProductRequest request) {
        return respond(ProductMapper.toResponse(service.save(ProductMapper.toDto(request))));
    }

    @GetMapping("{id}")
    public Response<ProductResponse> getShopAdminById(@PathVariable String id){
        return respond(ProductMapper.toResponse(service.getById(id)));
    }

    @PutMapping("{id}")
    public Response<ProductResponse> update(@PathVariable String id, @RequestBody ProductRequest productRequest){
        return respond(ProductMapper.toResponse(service.update(ProductMapper.toDto(productRequest),id)));
    }

    @DeleteMapping("/{id}")
    public Response<Void> deleteProduct(@PathVariable String id) {
        service.delete(id);
        return new Response<>(MetaResponse.success());
    }

    /*@GetMapping("category/{categoryId}")
    @PreAuthorize("hasAnyAuthority('super_admin','product_read')")
    @Log(response = false)
    public Response<PageResponse<ProductResponse>> getProductsByCategoryId(@PathVariable String categoryId, Pageable pageable) {
        return respond(ProductMapper.toPageResponse(service.findProductsByCategoryId(categoryId,pageable)));
    }*/
}
