package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
/**
 * The interface Auth service.
 */
public interface AuthService {
    String register(RegisterRequest registerRequest);
    JwtResponse login(LoginRequest loginRequest);
}
