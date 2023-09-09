package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.UsersRepository;
import com.openclassrooms.mddapi.security.service.UsersDetailsService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersDetailsService usersDetailsService;

    public Users getUserProfile() {
        return this.usersDetailsService.getUser();
    }

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
