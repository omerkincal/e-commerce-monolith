package com.example.ecommercewebapp.domain.platform.basket.web;


import com.example.ecommercewebapp.domain.platform.basket.api.BasketMapper;
import com.example.ecommercewebapp.domain.platform.basket.api.BasketService;
import com.example.ecommercewebapp.domain.platform.category.api.CategoryMapper;
import com.example.ecommercewebapp.domain.platform.category.web.CategoryResponse;
import com.example.ecommercewebapp.library.rest.BaseController;
import com.example.ecommercewebapp.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("baskets")
@RequiredArgsConstructor
public class BasketController extends BaseController {
    private final BasketService service;

    @PostMapping("add-product-to-basket")
    public Response<BasketResponse> addProductToBasket(@RequestBody BasketRequest addBasketRequest){
        return respond(BasketMapper.toResponse(service.addProductToBasket(BasketMapper.toDto(addBasketRequest))));
    }

    @GetMapping("{customerId}")
    public Response<BasketResponse> getBasketById(@PathVariable String customerId){
        return respond(BasketMapper.toResponse(service.getBasketById(customerId)));
    }


    @DeleteMapping("{basketItemId}")
    public String delete(@PathVariable String basketItemId){
        return service.removeProductFromBasket(basketItemId);
    }
}
