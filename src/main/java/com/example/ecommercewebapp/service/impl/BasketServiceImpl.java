package com.example.ecommercewebapp.service.impl;

import com.example.ecommercewebapp.dto.BasketDto;
import com.example.ecommercewebapp.dto.BasketItemDto;
import com.example.ecommercewebapp.entity.Basket;
import com.example.ecommercewebapp.entity.BasketItem;
import com.example.ecommercewebapp.entity.Customer;
import com.example.ecommercewebapp.entity.Product;
import com.example.ecommercewebapp.repository.BasketRepository;
import com.example.ecommercewebapp.service.BasketService;
import com.example.ecommercewebapp.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService{

    private final BasketRepository repository;

    private final CustomerService customerService;

    private final ProductServiceImpl productService;

    private final BasketItemServiceImpl basketItemService;


    public final int BASKET_STATUS_NONE = 0;
    public final int BASKET_STATUS_SALED = 1;



    @Override
    public BasketDto addProductToBasket(BasketDto basketDto) {
        if(basketDto.getBasketItemList().get(0).getProduct().getQuantity() < basketDto.getBasketItemList().get(0).getCount()){
            throw new ErrorResponseException(HttpStatus.BAD_REQUEST);
        }
        Basket basket = repository.findBasketByCustomer_CustomerIdAndStatusEquals(basketDto.getCustomer().getCustomerId(), BASKET_STATUS_NONE);
        if (basket != null){
           return sepetVarUrunEkle(basket, basketDto);
        }else {
          return sepetYokYeniSepetOlustur(basketDto);
        }
    }

    private BasketDto sepetYokYeniSepetOlustur(BasketDto basketDto) {
        Customer customer = customerService.getCustomerEntity(String.valueOf(basketDto.getCustomer().getCustomerId()));
        Basket basket = new Basket();
        basket.setCustomer(customer);
        basket.setStatus(BASKET_STATUS_NONE);
        List<BasketItem> basketItemList = new ArrayList<>();
        for(BasketItemDto dto: basketDto.getBasketItemList()){
            BasketItem basketItem = new BasketItem();
            basketItem.setCount(dto.getCount());
            Product product = productService.getProductEntity(String.valueOf(dto.getProduct().getProductId()));
            basketItem.setProduct(product);
            basketItem.setBasketItemAmount(dto.getCount()*product.getPrice());
            basketItem.setBasket(basket);
            basketItemList.add(basketItem);
        }
        basket.setBasketItemList(basketItemList);
        basket.setTotalAmount(basket.getBasketItemList().get(0).getCount()* basketItemList.get(0).getProduct().getPrice());
        basket =repository.save(basket);
        return toDto(basket);
    }

    private BasketDto sepetVarUrunEkle(Basket basket, BasketDto basketDto) {
        List<BasketItem> basketItemList = basket.getBasketItemList();
        //bu satır bir basketıtemın bir sepette zaten varmı yoksa ilk defa mı eklendiğini kontrol eder
        BasketItem basketItem = basketItemService.findBasketItemByBasketIdAndProductId(basket.getBasketId(), basketDto.getBasketItemList().get(0).getProduct().getProductId());
        if (basketItem != null){
            Product product = basketItem.getProduct();
            basketItem.setProduct(product);
            basketItem.setCount(basketDto.getBasketItemList().get(0).getCount());
            basketItem.setBasketItemAmount(basketItem.getCount()*product.getPrice());
            basketItemService.save(basketItem);
        }else {
            BasketItem newBasketItem = new BasketItem();
            //Product product = productService.getProductEntity(String.valueOf(basketDto.getBasketItemList().get(0).getProduct().getProductId()));
            Product product = productService.getProductEntity(String.valueOf(basketDto.getBasketItemList().get(0).getProduct().getProductId()));
            newBasketItem.setProduct(product);
            newBasketItem.setCount(basketDto.getBasketItemList().get(0).getCount());
            newBasketItem.setBasketItemAmount(newBasketItem.getCount()*product.getPrice());
            newBasketItem = basketItemService.save(newBasketItem);
            newBasketItem.setBasket(basket);
            basketItemList.add(newBasketItem);
        }
        basket.setBasketItemList(basketItemList);
        basket.setTotalAmount(calculateBasketAmount(basket.getBasketId()));
        basket = repository.save(basket);
        return toDto(basket);
    }

    private double calculateBasketAmount(int basketId) {
        Basket basket = repository.findBasketByBasketId(basketId);
        double totalAmount = 0;
        for (BasketItem basketItem : basket.getBasketItemList()){
            totalAmount += basketItem.getBasketItemAmount();
        }
        return totalAmount;
    }



    @Override
    public BasketDto getBasketById(String customerId) {
        return toDto(repository.findById(Integer.valueOf(customerId)).get());
    }

    @Transactional
    @Override
    public String removeProductFromBasket(String basketItemId) {
        basketItemService.delete(Integer.parseInt(basketItemId));
        return "Başarılı bir şekilde silindi";


        /*Basket basket = repository.findBasketByCustomer_CustomerIdAndStatusEquals(Integer.parseInt(customerId), BASKET_STATUS_NONE);
        List<BasketItem> basketItemList = basket.getBasketItemList();
        for (BasketItem basketItem : basketItemList){
            if (basketItem.getProduct().getProductId() == productId){
                basketItemService.delete(basketItem);
            }
        }
        basket.setBasketItemList(basketItemList);
        repository.save(basket);*/
    }


    public BasketDto toDto(Basket basket) {
        return BasketDto.builder()
                .basketId(basket.getBasketId())
                .customer(customerService.toDto(basket.getCustomer()))
                .basketItemList(basket.getBasketItemList()
                        .stream()
                        .map(basketItem -> basketItemService.toDto(basketItem))
                        .collect(Collectors.toList()))
                .totalAmount(basket.getTotalAmount())
                .build();
    }


    public Basket getBasketEntity(String id) {
        return repository.findById(Integer.parseInt(id)).get();
    }
}
