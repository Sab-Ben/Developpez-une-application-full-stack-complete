package com.openclassrooms.mddapi.security.service;

import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
/**
 * The type User details service.
 */
@Service
public class UsersDetailsService {

        private final UsersRepository usersRepository;

    public UsersDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Users getUser() {
            Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return this.usersRepository.findById(user.getId()).orElseThrow();
        }
}