package com.example.ecommercewebapp.library.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Mapper {
    public static <T, U> U map(T source, Class<U> destinationClass) {
        return map(source, destinationClass,new ArrayList<>());
    }

    public static <T, U> U map(T source, Class<U> destinationClass, List<String> notInclude) {
        try {
            U destination = destinationClass.getDeclaredConstructor().newInstance();

            ArrayList<Field> sourceFields = new ArrayList<>(Arrays.stream(source.getClass().getDeclaredFields()).toList());
            sourceFields.addAll(Arrays.stream(source.getClass().getSuperclass().getDeclaredFields()).toList());
            ArrayList<Field> destinationFields = new ArrayList<>(Arrays.stream(destinationClass.getDeclaredFields()).toList());
            destinationFields.addAll(Arrays.stream(destinationClass.getSuperclass().getDeclaredFields()).toList());

            for (Field sourceField : sourceFields) {
                if(notInclude.contains(sourceField.getName())) {
                    continue;
                }
                for (Field destinationField : destinationFields) {
                    try{
                        if (sourceField.getName().equals(destinationField.getName())) {
                            if(sourceField.getType().equals(destinationField.getType())){
                                sourceField.setAccessible(true);
                                destinationField.setAccessible(true);

                                Object value = sourceField.get(source);
                                destinationField.set(destination, value);
                            }
                            else{

                                sourceField.setAccessible(true);
                                destinationField.setAccessible(true);
                                Object value = sourceField.get(source);
                                if(destinationField.getType() == String.class){
                                    destinationField.set(destination, value.toString());
                                } else if (destinationField.getType() == long.class || destinationField.getType() == Long.class) {
                                    destinationField.set(destination, Long.parseLong(value.toString()));
                                }
                                else if (destinationField.getType() == int.class || destinationField.getType() == Integer.class) {
                                    destinationField.set(destination, Integer.parseInt(value.toString()));
                                }
                                else if (destinationField.getType() == short.class || destinationField.getType() == Short.class) {
                                    destinationField.set(destination, Short.parseShort(value.toString()));
                                }
                                else if (destinationField.getType() == boolean.class || destinationField.getType() == Boolean.class) {
                                    destinationField.set(destination, Boolean.parseBoolean(value.toString()));
                                }
                                else if (destinationField.getType() == float.class || destinationField.getType() == Float.class) {
                                    destinationField.set(destination, Float.parseFloat(value.toString()));
                                }
                                else if (destinationField.getType() == BigDecimal.class) {
                                    destinationField.set(destination, BigDecimal.valueOf(Double.parseDouble(value.toString())));
                                }
                                else if (destinationField.getType() == double.class || destinationField.getType() == Double.class) {
                                    destinationField.set(destination, Double.parseDouble(value.toString()));
                                }
                                else if (destinationField.getType() == Date.class) {
                                    destinationField.set(destination, DateFormat.getDateInstance().parse(value.toString()));
                                }
                            }
                        }
                    }
                    catch (Exception e){
                        //continue
                    }

                }
            }

            return destination;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
