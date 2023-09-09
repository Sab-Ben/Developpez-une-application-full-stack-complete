package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.UsersDto;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.service.UsersServiceImpl;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private UsersServiceImpl usersService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/me")
    public ResponseEntity<?> findUser() {
        Users user = this.usersService.getUserProfile();
        return ResponseEntity.ok(this.modelMapper.map(user, UsersDto.class));
    }

    @PutMapping("/me")
    public ResponseEntity<?> update(@Valid @RequestBody UsersDto usersDto) {
        Users user = this.usersService.updateUserProfile(this.modelMapper.map(usersDto, Users.class));
        return ResponseEntity.ok(this.modelMapper.map(user, UsersDto.class));
    }
}