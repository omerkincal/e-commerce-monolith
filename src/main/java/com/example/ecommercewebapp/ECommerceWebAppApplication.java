package com.example.ecommercewebapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaAuditing
public class ECommerceWebAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECommerceWebAppApplication.class, args);
    }

}
