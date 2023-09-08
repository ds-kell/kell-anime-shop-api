package com.kell.service;

import com.kell.webapp.dto.request.LoginRequest;
import com.kell.webapp.dto.request.SignupRequest;
import com.kell.webapp.dto.response.JwtResponse;


public interface AuthService {
    JwtResponse authenticateAccount(LoginRequest loginRequest);

    void registerAccount(SignupRequest signupRequest);

    String verifyExpiration(String refreshToken);

}
