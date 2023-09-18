package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.UnauthorizedException;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.payload.request.LoginRequest;
import com.openclassrooms.mddapi.payload.request.RegisterRequest;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import com.openclassrooms.mddapi.repository.UsersRepository;
import com.openclassrooms.mddapi.security.jwt.JwtProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Auth service implements.
 *
 *  @author Sabrina BENSEGHIR
 *  @version 1.0
 *  @since   01-09-2023
 */
@Service
public class AuthServiceImpl implements AuthService{
    /**
     * The Users Repository.
     */
    private final UsersRepository usersRepository;

    /**
     * The BCryptPasswordEncoder.
     */
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * The JwtProvider.
     */
    private final JwtProvider jwtProvider;

    /**
     * Instantiates a new Authentication Service.
     *
     * @param usersRepository the users repository
     * @param passwordEncoder the password encoder
     * @param jwtProvider the JWT provider
     */
    public AuthServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder, JwtProvider jwtProvider){
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    /**
     * Performs user authentication and generates a JWT response upon successful login.
     *
     * @param loginRequest The login request containing the username and password.
     * @return A JWT response containing the authentication token if login is successful.
     * @throws UnauthorizedException If the provided credentials are invalid.
     */
    public JwtResponse login(LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Users users = this.usersRepository.findByEmailOrUsername(username);

        if (!this.isUserValid(users, password)){
            throw new UnauthorizedException();
        }

        return this.jwtProvider.provideJwt(users);
    }

    /**
     * Registers a new user with the provided registration information.
     *
     * @param registerRequest The registration request containing user's email, username, and password.
     * @return A success message upon successful user registration.
     * @throws BadRequestException If the provided registration information is invalid or conflicts with existing data.
     */
    public String register(RegisterRequest registerRequest){
        String email = registerRequest.getEmail();
        String username = registerRequest.getUsername();
        String password = registerRequest.getPassword();

        if (this.usersRepository.existsByEmailOrUsername(email, username) != 0){
            throw new BadRequestException();
        }

        if (!this.isValidRegistrationPassword(password)){
            throw new BadRequestException();
        }

        String hashedPassword = this.passwordEncoder.encode(password);

        Users user = new Users(
                email,
                username,
                hashedPassword
        );

        this.usersRepository.save(user);

        return "User registered successfully";
    }

    /**
     * Checks if a provided password meets the criteria for a valid registration password.
     *
     * @param password The password to validate.
     * @return {@code true} if the password meets the criteria, {@code false} otherwise.
     */
    public boolean isValidRegistrationPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+!=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }


    /**
     * Checks if a user is valid by comparing their stored password hash with the provided raw password.
     *
     * @param users The user object to validate.
     * @param rawPassword The raw password to compare with the user's stored password hash.
     * @return {@code true} if the user is valid, {@code false} otherwise.
     */
    private boolean isUserValid(Users users, String rawPassword){
        return users != null && this.passwordEncoder.matches(rawPassword, users.getPassword());
    }

}
