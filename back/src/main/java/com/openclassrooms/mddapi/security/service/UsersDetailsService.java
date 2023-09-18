package com.openclassrooms.mddapi.security.service;

import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
/**
 * The type User details service.
 *
 *  @author Sabrina BENSEGHIR
 *  @version 1.0
 *  @since   01-09-2023
 */
@Service
public class UsersDetailsService {

    /**
     * The Users Repository.
     */
    private final UsersRepository usersRepository;

    /**
     * Instantiates a new User Detail Service.
     *
     * @param usersRepository the user repository
     */
    public UsersDetailsService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Retrieves the currently authenticated user from the security context
     * and returns the corresponding user from the user repository.
     *
     * @return The currently authenticated user.
     */
    public Users getUser() {
            Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return this.usersRepository.findById(user.getId()).orElseThrow();
        }
}