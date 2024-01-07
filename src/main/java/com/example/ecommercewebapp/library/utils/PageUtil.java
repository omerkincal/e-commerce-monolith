package com.example.ecommercewebapp.library.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

public class PageUtil {
    private PageUtil(){}
    public static <T,R> Page<T> pageToDto(Page<R> page, Callable<T,R> toDto){
        List<T> list = page.stream().map(toDto::call).toList();
        return new PageImpl<>(list,page.getPageable(),page.getTotalElements());
    }
}
