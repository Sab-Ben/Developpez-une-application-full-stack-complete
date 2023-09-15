package com.openclassrooms.mddapi.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
/**
 * The type Login Request.
 */
@Data
public class LoginRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
