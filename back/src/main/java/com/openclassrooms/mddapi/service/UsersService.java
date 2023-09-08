package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.models.Users;

public interface UsersService {

    Users findById(Long id);
    Users getUserProfile();
    Users updateUserProfile(Users user);

    void deleteById(Long id);
}
