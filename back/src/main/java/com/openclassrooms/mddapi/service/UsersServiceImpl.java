package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.repository.UsersRepository;
import com.openclassrooms.mddapi.security.UsersDetailsService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private UsersDetailsService usersDetailsService;

    public Users getUserProfile() {
        return this.usersDetailsService.getUser();
    }

    public Users updateUserProfile(Users users)  {
        Users authenticatedUser = this.usersDetailsService.getUser();
        users.setId(authenticatedUser.getId());
        users.setPassword(authenticatedUser.getPassword());

        try {
            this.usersRepository.save(users);
        } catch (ConstraintViolationException e) {
            throw new BadRequestException();
        }

        return users;
    }

    public void deleteById(Long id) {
        this.usersRepository.deleteById(id);
    }

    public Users findById(Long id) {
        return this.usersRepository.findById(id).orElse(null);
    }
}
