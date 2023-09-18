package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.UsersRepository;
import com.openclassrooms.mddapi.security.service.UsersDetailsService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Service;
/**
 * The type Users service implements.
 *
 *  @author Sabrina BENSEGHIR
 *  @version 1.0
 *  @since   01-09-2023
 */
@Service
public class UsersServiceImpl implements UsersService {

    /**
     * The Users Repository.
     */
    private final UsersRepository usersRepository;

    /**
     * The Users Detail Service.
     */
    private final UsersDetailsService usersDetailsService;

    /**
     * Instantiates a new User Service.
     *
     * @param usersRepository the user repository
     * @param usersDetailsService the user details service
     */

    public UsersServiceImpl(UsersRepository usersRepository, UsersDetailsService usersDetailsService) {
        this.usersRepository = usersRepository;
        this.usersDetailsService = usersDetailsService;
    }

    /**
     * Retrieves the currently authenticated user from the user
     * details service.
     *
     * @return The currently authenticated user.
     */
    public Users getUserProfile() {
        return this.usersDetailsService.getUser();
    }


    /**
     * Updates the user profile information for the currently authenticated user.
     * The method retrieves the authenticated user's information, updates the provided user object
     * with the authenticated user's ID and password, and saves the changes to the user repository.
     *
     * @param user The user object containing the updated profile information.
     * @return The updated user profile.
     * @throws BadRequestException If there are validation constraints or data integrity issues while saving the user's profile.
     */
    public Users updateUserProfile(Users user)  {
        Users authenticatedUser = this.usersDetailsService.getUser();
        user.setId(authenticatedUser.getId());
        user.setPassword(authenticatedUser.getPassword());

        try {
            this.usersRepository.save(user);
        } catch (ConstraintViolationException e) {
            throw new BadRequestException();
        }

        return user;
    }
}
