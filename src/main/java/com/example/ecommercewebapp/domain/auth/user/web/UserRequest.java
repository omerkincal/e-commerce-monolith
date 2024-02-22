package com.example.ecommercewebapp.domain.auth.user.web;

import com.example.ecommercewebapp.domain.auth.user.api.UserType;
import lombok.*;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class UserRequest {
    private final String name;
    private final String surname;
    private final String email;
    private final String phoneNumber;
    private final String extensionNumber;
    private final Boolean status;
    private final UserType userType;
    private final List<String> userGroupIds;
}
