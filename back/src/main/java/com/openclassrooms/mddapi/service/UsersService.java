package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.models.Users;
/**
 * The interface Users service.
 */
public interface UsersService {

    Users getUserProfile();
    Users updateUserProfile(Users user);
}
