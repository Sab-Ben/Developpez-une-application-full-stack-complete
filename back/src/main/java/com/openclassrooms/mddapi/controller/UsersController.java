package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.mapper.UsersMapper;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UsersController {
    private final UsersMapper usersMapper;
    private final UsersService usersService;


    public UsersController(UsersService usersService,
                           UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
        this.usersService = usersService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            Users users = this.usersService.findById(Long.valueOf(id));

            if (users == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.usersMapper.toDto(users));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> save(@PathVariable("id") String id) {
        try {
            Users users = this.usersService.findById(Long.valueOf(id));

            if (users == null) {
                return ResponseEntity.notFound().build();
            }

            UserDetails usersDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (!Objects.equals(usersDetails.getUsername(), users.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            this.usersService.deleteById(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}