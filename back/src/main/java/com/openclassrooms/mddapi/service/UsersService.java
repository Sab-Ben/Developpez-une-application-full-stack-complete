package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.models.Users;

public interface UsersService {

    Users getUserProfile();
    Users updateUserProfile(Users users);
}
