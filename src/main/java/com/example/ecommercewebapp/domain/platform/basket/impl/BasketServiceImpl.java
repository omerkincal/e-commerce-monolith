package com.example.ecommercewebapp.domain.platform.basket.impl;

import com.example.ecommercewebapp.domain.platform.basket.api.BasketDto;
import com.example.ecommercewebapp.domain.platform.basket.api.BasketService;
import com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct.BasketProductServiceImpl;
import com.example.ecommercewebapp.domain.platform.customer.api.CustomerService;
import com.example.ecommercewebapp.domain.platform.product.impl.ProductServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class BasketServiceImpl implements BasketService {

    /*private BasketRepository repository;

    private CustomerService customerService;

    private ProductServiceImpl productService;

    private BasketProductServiceImpl basketItemService;


    public final int BASKET_STATUS_NONE = 0;
    public final int BASKET_STATUS_SALED = 1;

    public BasketServiceImpl(BasketRepository repository, CustomerService customerService, ProductServiceImpl productService, BasketProductServiceImpl basketItemService) {
        this.repository = repository;
        this.customerService = customerService;
        this.productService = productService;
        this.basketItemService = basketItemService;
    }*/

    @Override
    public BasketDto addProductToBasket(BasketDto basketDto) {
        return null;
    }

    @Override
    public BasketDto getBasketById(String customerId) {
        return null;
    }

    @Override
    public String removeProductFromBasket(String basketItemId) {
        return null;
    }

/*

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
        List<BasketProduct> basketProductList = new ArrayList<>();
        for(BasketProductDto dto: basketDto.getBasketItemList()){
            BasketProduct basketProduct = new BasketProduct();
            basketProduct.setCount(dto.getCount());
            Product product = productService.getProductEntity(String.valueOf(dto.getProduct().getProductId()));
            basketProduct.setProduct(product);
            basketProduct.setBasketItemAmount(dto.getCount()*product.getPrice());
            basketProduct.setBasket(basket);
            basketProductList.add(basketProduct);
        }
        basket.setBasketItemList(basketProductList);
        basket.setTotalAmount(basket.getBasketItemList().get(0).getCount()* basketProductList.get(0).getProduct().getPrice());
        basket =repository.save(basket);
        return toDto(basket);
    }

    private BasketDto sepetVarUrunEkle(Basket basket, BasketDto basketDto) {
        List<BasketProduct> basketProductList = basket.getBasketItemList();
        //bu satır bir basketıtemın bir sepette zaten varmı yoksa ilk defa mı eklendiğini kontrol eder
        BasketProduct basketProduct = basketItemService.findBasketItemByBasketIdAndProductId(basket.getBasketId(), basketDto.getBasketItemList().get(0).getProduct().getProductId());
        if (basketProduct != null){
            Product product = basketProduct.getProduct();
            basketProduct.setProduct(product);
            basketProduct.setCount(basketDto.getBasketItemList().get(0).getCount());
            basketProduct.setBasketItemAmount(basketProduct.getCount()*product.getPrice());
            basketItemService.save(basketProduct);
        }else {
            BasketProduct newBasketProduct = new BasketProduct();
            //Product product = productService.getProductEntity(String.valueOf(basketDto.getBasketItemList().get(0).getProduct().getProductId()));
            Product product = productService.getProductEntity(String.valueOf(basketDto.getBasketItemList().get(0).getProduct().getProductId()));
            newBasketProduct.setProduct(product);
            newBasketProduct.setCount(basketDto.getBasketItemList().get(0).getCount());
            newBasketProduct.setBasketItemAmount(newBasketProduct.getCount()*product.getPrice());
            newBasketProduct = basketItemService.save(newBasketProduct);
            newBasketProduct.setBasket(basket);
            basketProductList.add(newBasketProduct);
        }
        basket.setBasketItemList(basketProductList);
        basket.setTotalAmount(calculateBasketAmount(basket.getBasketId()));
        basket = repository.save(basket);
        return toDto(basket);
    }

    private double calculateBasketAmount(int basketId) {
        Basket basket = repository.findBasketByBasketId(basketId);
        double totalAmount = 0;
        for (BasketProduct basketProduct : basket.getBasketItemList()){
            totalAmount += basketProduct.getBasketItemAmount();
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
        List<BasketProduct> basketItemList = basket.getBasketItemList();
        for (BasketProduct basketItem : basketItemList){
            if (basketItem.getProduct().getProductId() == productId){
                basketItemService.delete(basketItem);
            }
        }
        basket.setBasketItemList(basketItemList);
        repository.save(basket);*/
}
