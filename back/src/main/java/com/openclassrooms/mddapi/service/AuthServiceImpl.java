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
 */
@Service
public class AuthServiceImpl implements AuthService{
    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    public AuthServiceImpl(UsersRepository userRepository, UsersRepository usersRepository, BCryptPasswordEncoder passwordEncoder, JwtProvider jwtProvider){
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public JwtResponse login(LoginRequest loginRequest){
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        Users users = this.usersRepository.findByEmailOrUsername(username);

        if (!this.isUserValid(users, password)){
            throw new UnauthorizedException();
        }

        return this.jwtProvider.provideJwt(users);
    }

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

    public boolean isValidRegistrationPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+!=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

    private boolean isUserValid(Users users, String rawPassword){
        return users != null && this.passwordEncoder.matches(rawPassword, users.getPassword());
    }

}
