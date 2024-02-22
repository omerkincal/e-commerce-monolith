package com.example.ecommercewebapp.library.utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.Set;

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



    public static List<String> getPage(Set<String> set, int pageSize, int pageNumber) {
        List<String> list = List.copyOf(set);
        int startIdx = pageNumber * pageSize;
        int endIdx = Math.min((pageNumber + 1) * pageSize, list.size());
        return list.subList(startIdx, endIdx);
    }

    public static String generateRandomPassword() {
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            password.append(random.nextInt(10));
        }
        return password.toString();
    }
}
