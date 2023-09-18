package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UsersDto;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * The type User controller.
 *
 *  @author Sabrina BENSEGHIR
 *  @version 1.0
 *  @since   01-09-2023
 */

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class  UsersController implements SecurityController{
    /**
     * The User services.
     */
    private final UsersService usersService;

    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Instantiates a new User controller.
     *
     * @param usersService  the user service
     * @param modelMapper the model
     */
    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }


    /**
     * Get the user information.
     *
     * @return A ResponseEntity with the user's information in the form of a DTO (Data Transfer Object)
     *         upon successful retrieval or an error message for unauthorized access.
     */
    @Operation(summary = "Get the user information")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    }
    )
    @GetMapping("/me")
    public ResponseEntity<?> findUser() {
        Users user = this.usersService.getUserProfile();
        return ResponseEntity.ok(this.modelMapper.map(user, UsersDto.class));
    }


    /**
     * Update user profile.
     *
     * @param usersDto The user data transfer object (DTO) containing updated user profile information.
     * @return A ResponseEntity with the updated user profile information in the form of a DTO upon
     *         successful update or an error message for unauthorized access.
     */
    @Operation(summary = "Update user profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", content = @Content)
    })
    @PutMapping("/me")
    public ResponseEntity<?> update(@Valid @RequestBody UsersDto usersDto) {
        Users user = this.usersService.updateUserProfile(this.modelMapper.map(usersDto, Users.class));
        return ResponseEntity.ok(this.modelMapper.map(user, UsersDto.class));
    }
}