package com.example.ecommercewebapp.domain.platform.basket.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BasketRequest {
    @NotNull(message = "validation.required.productId")
    @NotBlank(message = "validation.required.productId")
    @NotEmpty(message = "validation.required.productId")
    private String productId;

    @NotNull(message = "validation.required.quantity")
    private Integer quantity;

    @NotNull(message = "validation.required.userId")
    @NotBlank(message = "validation.required.userId")
    @NotEmpty(message = "validation.required.userId")
    private String userId;
}
