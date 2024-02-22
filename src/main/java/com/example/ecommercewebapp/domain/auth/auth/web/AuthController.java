package com.example.ecommercewebapp.domain.auth.auth.web;

import com.example.ecommercewebapp.domain.auth.auth.api.AuthMapper;
import com.example.ecommercewebapp.domain.auth.auth.api.AuthService;
import com.example.ecommercewebapp.domain.auth.user.api.UserType;
import com.example.ecommercewebapp.library.rest.BaseController;
import com.example.ecommercewebapp.library.rest.MetaResponse;
import com.example.ecommercewebapp.library.rest.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController extends BaseController {

    private final AuthService authService;

    @PostMapping("/login")
    public Response<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        return respond(AuthMapper.toResponse(authService.login(AuthMapper.toDto(loginRequest, UserType.CUSTOMER))));
    }

    @PostMapping("/login-admin")
    public Response<LoginResponse> loginAdmin(@RequestBody LoginRequest loginRequest){
        return respond(AuthMapper.toResponse(authService.login(AuthMapper.toDto(loginRequest, UserType.ADMIN))));
    }


    @PostMapping("/login-super-admin")
    public Response<LoginResponse> loginSuperAdmin(@RequestBody LoginRequest loginRequest){
        return respond(AuthMapper.toResponse(authService.login(AuthMapper.toDto(loginRequest, UserType.SUPER_ADMIN))));
    }

    @PostMapping("/sign-up")
    public Response<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        return respond(AuthMapper.toResponseSignUp(authService.signUp(AuthMapper.toDto(signUpRequest, UserType.CUSTOMER))));
    }

    @PostMapping("/change-password")
    public Response<String> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return respond(authService.changePassword(AuthMapper.toDto(changePasswordRequest, UserType.CUSTOMER)));
    }

    @PostMapping("/reset-password")
    public  Response<Void> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        authService.resetPassword(AuthMapper.toDto(passwordResetRequest, UserType.CUSTOMER));
        return new Response<>(MetaResponse.success());
    }
}
