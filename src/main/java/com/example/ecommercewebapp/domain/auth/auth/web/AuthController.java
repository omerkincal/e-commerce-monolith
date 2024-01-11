package com.example.ecommercewebapp.domain.auth.auth.web;

import com.example.ecommercewebapp.domain.auth.auth.api.AuthMapper;
import com.example.ecommercewebapp.domain.auth.auth.api.AuthService;
import com.example.ecommercewebapp.domain.auth.user.impl.UserType;
import com.example.ecommercewebapp.library.rest.BaseController;
import com.example.ecommercewebapp.library.rest.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController extends BaseController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("admin/login")
    public Response<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return respond(AuthMapper.toResponse(authService.login(AuthMapper.toDto(loginRequest, UserType.ADMIN))));
    }

    @PostMapping("sign-up")
    public Response<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest) {
        return respond(AuthMapper.toSignUpResponse(authService.signUp(AuthMapper.toDto(signUpRequest, UserType.CUSTOMER))));
    }


}
