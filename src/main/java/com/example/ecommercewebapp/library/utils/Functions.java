package com.example.ecommercewebapp.library.utils;

public class Functions {

    private Functions() {}

    public static String maskCardNumber(String cardNumber, int start, int end) {
        StringBuilder maskedNumber = new StringBuilder(cardNumber);
        for (int i = start; i < end; i++) {
            maskedNumber.setCharAt(i, '*');
        }
        return maskedNumber.toString();
    }

    public static String maskCardNumber(String cardNumber) {
        return maskCardNumber(cardNumber,8,12);
    }
}
