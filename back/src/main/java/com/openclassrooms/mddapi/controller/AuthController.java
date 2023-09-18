package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.AuthService;
import com.openclassrooms.mddapi.service.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Auth controller implements Security controller.
 * @author Sabrina BENSEGHIR
 * @version 1.0
 * @since   01-09-2023
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController implements SecurityController{
    /**
     * The Authentication service.
     */
    private final AuthService authService;

    /**
     * Instantiates a new Auth controller.
     *
     * @param authService the authentication service
     */

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    /**
     * Authenticate the user.
     *
     * @param loginRequest The login request containing the username and password.
     * @return A ResponseEntity with a JWT response upon successful login or an error message for unauthorized access.
     */
    @Operation(summary = "Authenticate the user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            JwtResponse jwt = this.authService.login(loginRequest);
            return ResponseEntity.ok(jwt);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new MessageResponse("Unauthorized"));
        }
    }

    /**
     * Create a new user.
     *
     * @param registerRequest The registration request containing user's email, username, and password.
     * @return A ResponseEntity with a success message upon successful user creation or an error message for a bad request.
     */
    @Operation(summary = "Create a new user")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content)
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            String response = this.authService.register(registerRequest);
            return ResponseEntity.ok(new MessageResponse(response));
        } catch (BadRequestException exception) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: username or email already taken"));
        }
    }
}
