package com.openclassrooms.mddapi.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.openclassrooms.mddapi.models.Users;
import com.openclassrooms.mddapi.payload.response.JwtResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
/**
 * The type Jwt provider.
 */
@Service
public class JwtProvider  {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public JwtResponse provideJwt(Users user){
      JwtResponse jwtResponse = new JwtResponse();
      final String token = this.encodeJwt(user);
      jwtResponse.setToken(token);

      return jwtResponse;
    }

    private String encodeJwt(Users user) {
      final long ONE_HOUR_MILLISECONDS = 60 * 60 * 1000L;
      final long ONE_DAY_MILLISECONDS = ONE_HOUR_MILLISECONDS * 24L;
      final long TOKEN_DURATION = ONE_DAY_MILLISECONDS * 30L;
      Date expirationDate = new Date(System.currentTimeMillis() + TOKEN_DURATION);

      return JWT.create()
              .withSubject(user.getId().toString())
              .withIssuedAt(new Date(System.currentTimeMillis()))
              .withExpiresAt(expirationDate)
              .sign(getAlgorithm());
    }

    public DecodedJWT decodeJwt(String token){
      return JWT
              .require(getAlgorithm())
              .build()
              .verify(token);
    }

    public Long getSubject(DecodedJWT decodedJwt){
      String sub = decodedJwt.getSubject();
      return Long.parseLong(sub);
    }

    private Algorithm getAlgorithm(){
      return Algorithm.HMAC256(jwtSecret);
    }
}
