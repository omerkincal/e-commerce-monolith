package com.example.ecommercewebapp.domain.auth.auth.api;

public interface AuthService {
    TokenDto login(LoginDto loginDto);

    TokenDto signUp(SignUpDto signUpDto);
    /*TokenDto signUp(SignUpDto signUpDto);
    String changePassword(ChangePasswordDto changePasswordDto);

    void resetPassword(PasswordResetDto passwordResetDto);*/
}
