package com.openclassrooms.mddapi.security;

import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsersDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    public Users getUser() {
        Users users = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return this.usersRepository.findById(users.getId()).orElseThrow();
    }
}