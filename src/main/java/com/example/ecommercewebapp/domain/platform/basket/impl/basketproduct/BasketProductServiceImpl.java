package com.example.ecommercewebapp.domain.platform.basket.impl.basketproduct;

import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductDto;
import com.example.ecommercewebapp.domain.platform.basket.api.basketproduct.BasketProductService;
import com.example.ecommercewebapp.domain.platform.product.api.ProductService;
import com.example.ecommercewebapp.library.enums.MessageCodes;
import com.example.ecommercewebapp.library.exception.CoreException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketProductServiceImpl implements BasketProductService {

    private BasketProductRepository repository;
    private ProductService productService;

    public BasketProductServiceImpl(BasketProductRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    @Override
    public BasketProductDto save(BasketProduct basketProduct) {
        return BasketProductMapper.toDto(repository.save(basketProduct), productService);
    }

    @Override
    public BasketProductDto getById(String id) {
        return BasketProductMapper.toDto(repository.findById(id).orElseThrow(
                () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, "Sepette ürün bulunamadı", id)), productService);
    }

    @Override
    public BasketProductDto update(String id, BasketProduct basketProduct) {
        BasketProduct product = repository.findById(id).orElseThrow(
                () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, "Sepette ürün bulunamadı", id));
        product.setQuantity(basketProduct.getQuantity());
        product.setBasketProductAmount(basketProduct.getBasketProductAmount());
        return BasketProductMapper.toDto(repository.save(product), productService);
    }

    @Override
    public void delete(String id) {
        BasketProduct product = repository.findById(id).orElseThrow(
                () -> new CoreException(MessageCodes.ENTITY_NOT_FOUND, "Sepette ürün bulunamadı", id));
        repository.delete(product);
    }

    @Override
    public List<BasketProductDto> getAllByBasketId(String id) {
        List<BasketProductDto> basketProducts = repository.findAllByBasketId(id)
                .stream().map(basketProduct -> BasketProductMapper.toDto(basketProduct, productService))
                .collect(Collectors.toList());
        return basketProducts;
    }

    @Override
    public BasketProduct findBasketProductByBasketIdAndProductId(String basketProductId, String productId) {
        return repository.findAllByBasketIdAndProductId(basketProductId, productId);
    }
}
