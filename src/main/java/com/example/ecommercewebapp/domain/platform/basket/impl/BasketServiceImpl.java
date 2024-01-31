package com.example.ecommercewebapp.domain.platform.basket.impl;

import com.example.ecommercewebapp.domain.auth.user.api.UserDto;
import com.example.ecommercewebapp.domain.auth.user.api.UserService;
import com.example.ecommercewebapp.domain.platform.basket.api.BasketDto;
import com.example.ecommercewebapp.domain.platform.basket.api.BasketService;
import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductDto;
import com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct.BasketProduct;
import com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct.BasketProductServiceImpl;
import com.example.ecommercewebapp.domain.platform.product.api.ProductDto;
import com.example.ecommercewebapp.domain.platform.product.api.ProductService;
import com.example.ecommercewebapp.domain.platform.product.impl.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class BasketServiceImpl implements BasketService {

    private BasketRepository repository;
    private UserService userService;
    private ProductService productService;
    private BasketProductServiceImpl basketProductService;
    public final int BASKET_STATUS_NONE = 0;
    public final int BASKET_STATUS_SOLD = 1;

    public BasketServiceImpl(BasketRepository repository, UserService userService, ProductService productService, BasketProductServiceImpl basketProductService) {
        this.repository = repository;
        this.userService = userService;
        this.productService = productService;
        this.basketProductService = basketProductService;
    }

    @Override
    public BasketDto getBasketById(String customerId) {
        return null;
    }

    @Override
    public String removeProductFromBasket(String basketItemId) {
        return null;
    }


    @Override
    public BasketDto addProductToBasket(BasketDto basketDto) {
        /*if(productService.getById(basketDto.getProducts().get(0).getProductId()).getStock() < productService.getById(basketDto.getProducts().get(0).getProductId()).getStock()){
            throw new CoreException(MessageCodes.BAD_REQUEST, "Urun stokta yok", basketDto.getProducts().get(0).getProductId());
        }*/
        Basket basket = repository.findByUserIdAndStatusEquals(basketDto.getUser().getId(), BASKET_STATUS_NONE);
        if (basket != null){
            return basketIsExist(basket, basketDto);
        }else {
          return basketIsAbsent(basketDto);
        }
    }

    private BasketDto basketIsAbsent(BasketDto basketDto) {
        UserDto user = userService.getById(String.valueOf(basketDto.getUser().getId()));
        Basket basket = new Basket();
        basket.setUserId(user.getId());
        basket.setStatus(BASKET_STATUS_NONE);
        BasketProduct basketProduct = new BasketProduct();
        for(BasketProductDto dto: basketDto.getProducts()){
            basketProduct.setQuantity(dto.getQuantity());
            ProductDto product = productService.getById(String.valueOf(dto.getProduct().getId()));
            basketProduct.setProductId(product.getId());
            basketProduct.setBasketProductAmount(basketProduct.getQuantity() * product.getPrice());
        }
        basket = repository.save(basket);
        basketProduct.setBasketId(basket.getId());
        basketProductService.save(basketProduct);
        return BasketMapper.toDto(basket, basketProductService, userService);
    }

    private BasketDto basketIsExist(Basket basket, BasketDto basketDto) {
        List<BasketProductDto> basketProductList = basketProductService.getAllByBasketId(basket.getId());
        //bu satır bir basketıtemın bir sepette zaten varmı yoksa ilk defa mı eklendiğini kontrol eder
        BasketProduct basketProduct = basketProductService.findBasketProductByBasketIdAndProductId(basket.getId(), basketDto.getProducts().get(0).getProduct().getId());
        if (basketProduct != null){
            basketProductService.update(basketProduct.getId(), basketProduct);
        }else {
            /*BasketProduct newBasketProduct = new BasketProduct();
            //Product product = productService.getProductEntity(String.valueOf(basketDto.getBasketItemList().get(0).getProduct().getProductId()));
            Product product = productService.getProductEntity(String.valueOf(basketDto.getBasketItemList().get(0).getProduct().getProductId()));
            newBasketProduct.setProduct(product);
            newBasketProduct.setCount(basketDto.getBasketItemList().get(0).getCount());
            newBasketProduct.setBasketItemAmount(newBasketProduct.getCount()*product.getPrice());
            newBasketProduct = basketItemService.save(newBasketProduct);
            newBasketProduct.setBasket(basket);
            basketProductList.add(newBasketProduct);*/
        }
        basket.setTotalAmount(calculateBasketAmount(basket.getId()));
        basket = repository.save(basket);
        return BasketMapper.toDto(basket, basketProductService, userService);
    }

   private double calculateBasketAmount(String basketId) {
        Basket basket = repository.findById(basketId).get();
        List<BasketProductDto> basketProducts = basketProductService.getAllByBasketId(basketId);
        double totalAmount = 0;
        for (BasketProductDto basketProduct : basketProducts){
            totalAmount += basketProduct.getBasketProductAmount();
        }
        return totalAmount;
    }



 /*   @Override
    public BasketDto getBasketById(String customerId) {
        return toDto(repository.findById(Integer.valueOf(customerId)).get());
    }

    @Transactional
    @Override
    public String removeProductFromBasket(String basketItemId) {
        basketItemService.delete(Integer.parseInt(basketItemId));
        return "Başarılı bir şekilde silindi";


        /*Basket basket = repository.findBasketByCustomer_CustomerIdAndStatusEquals(Integer.parseInt(customerId), BASKET_STATUS_NONE);
        List<BasketProduct> basketItemList = basket.getBasketItemList();
        for (BasketProduct basketItem : basketItemList){
            if (basketItem.getProduct().getProductId() == productId){
                basketItemService.delete(basketItem);
            }
        }
        basket.setBasketItemList(basketItemList);
        repository.save(basket);*/
}
