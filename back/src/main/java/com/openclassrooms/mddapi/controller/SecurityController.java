package com.openclassrooms.mddapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
/**
 * The interface Security controller.
 *
 *  @author Sabrina BENSEGHIR
 *  @version 1.0
 *  @since   01-09-2023
 */
@SecurityRequirement(name = "bearerAuth")
public interface SecurityController {
}
