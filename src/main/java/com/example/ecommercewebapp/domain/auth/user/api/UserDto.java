package com.example.ecommercewebapp.domain.auth.user.api;

import com.example.ecommercewebapp.domain.auth.usergroup.api.UserGroupDto;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
@Builder
@ToString
public class UserDto {
    private final String id;
    private final Date created;
    private final Date modified;
    private final String name;
    private final String surname;
    private final String password;
    private final String email;
    private final String phoneNumber;
    private final String extensionNumber;
    private final Boolean status;
    private final Boolean verified;
    private final UserType userType;
    private final List<UserGroupDto> userGroups;

}
