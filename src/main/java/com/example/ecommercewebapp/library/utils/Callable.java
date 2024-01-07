package com.example.ecommercewebapp.library.utils;

@FunctionalInterface
public interface Callable<T,R> {
    T call(R r);
}
