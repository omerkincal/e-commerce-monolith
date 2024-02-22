package com.example.ecommercewebapp.domain.auth.permission.impl;

import com.example.ecommercewebapp.domain.auth.permission.api.DefaultPermissionCreatedEvent;
import com.example.ecommercewebapp.domain.auth.permission.api.DefaultPermissionCreatedForCustomerEvent;
import com.example.ecommercewebapp.domain.auth.permission.api.PermissionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultPermissionCreator {
    private static final String COUNTRY_WRITE ="country_write";
    private final PermissionServiceImpl service;
    public static final String SUPER_ADMIN = "super_admin";
    public static final String CUSTOMER = "customer";
    public static final String PRODUCT_WRITE = "product_write";
    public static final String MODEL_READ = "model_read";
    public static final String MODEL_WRITE = "model_write";


    private final ApplicationEventPublisher publisher;
    @EventListener
    @Order(1)
    public void creatDefaultPermission(ApplicationReadyEvent readyEvent) {
        var defaultSchemas = List.of("public");
        defaultSchemas.forEach(schema -> {
            List<PermissionDto> permissions = createPermissionDtos();
            service.createAll(permissions);
            Permission permission = service.create(PermissionDto.builder()
                            .displayName("Süper Admin Yetkisi")
                    .name(SUPER_ADMIN)
                    .type(PermissionType.SUPER_ADMIN)
                    .description("Süper Admin Yetkisi -> Bu yetkiye sahip olan kullanıcılar tüm yetkilere sahiptir.")
                    .build());
            publisher.publishEvent(new DefaultPermissionCreatedEvent(permission.getId()));

        });
    }

    @EventListener
    @Order(1)
    public void creatDefaultPermissionByCustomer(ApplicationReadyEvent readyEvent) {
        var defaultSchemas = List.of("public");
        defaultSchemas.forEach(schema -> {
            Permission permission = service.create(PermissionDto.builder()
                    .name(CUSTOMER)
                            .displayName("Müşteri Yetkisi")
                    .type(PermissionType.CUSTOMER)
                    .description("Bu yetkiye sahip olan kullanıcılar müşteri yetkilere sahiptir.")
                    .build());
            publisher.publishEvent(new DefaultPermissionCreatedForCustomerEvent(permission.getId()));
        });
    }


    private List<PermissionDto> createPermissionDtos() {
        List<PermissionDto> permissions = new ArrayList<>();
        permissions.add(PermissionDto.builder()
                .name(PRODUCT_WRITE)
                .type(PermissionType.USER)
                .displayName("Ürün Düzenleme Yetkisi")
                .description("Bu yetkiye sahip olan kullanıcılar ürünleri düzenleyebilir.")
                .build());


        permissions.add(PermissionDto.builder()
                .name(MODEL_READ)
                .type(PermissionType.USER)
                .displayName("Model Görüntüleme Yetkisi")
                .description("Bu yetkiye sahip olan kullanıcılar ürünleri görüntüleyebilir.")
                .build());


        permissions.add(PermissionDto.builder()
                .name(MODEL_WRITE)
                .type(PermissionType.USER)
                .displayName("Model Düzenleme Yetkisi")
                .description("Bu yetkiye sahip olan kullanıcılar ürünleri düzenleyebilir.")
                .build());

        return permissions;
    }

}




