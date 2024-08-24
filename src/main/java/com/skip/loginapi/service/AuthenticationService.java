package com.skip.loginapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.skip.loginapi.dto.LoginResponseDTO;
import com.skip.loginapi.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;

@Service
public class AuthenticationService {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.security.token.issuer}")
    private String issuer;

    public LoginResponseDTO generateToken(User user) throws JWTCreationException{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            Instant expiresAt = generateExpirationData();

            String subject = user.getId() + ","
                    + user.getFirstName() + ","
                    + user.getLastName() + ","
                    + user.getEmail() + ","
                    + user.getAuthorities() ;

            String token = JWT.create()
                    .withIssuer(issuer)
                    .withSubject(subject)
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);
            return new LoginResponseDTO(user,token,expiresAt);
    }

    public String validateToken(String token) throws JWTVerificationException{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(token)
                    .getSubject();
    }

    private Instant generateExpirationData() {
        Instant utcInstant = Instant.now();
        ZoneId brasiliaZone = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime brasiliaTime = utcInstant.atZone(brasiliaZone);

        return brasiliaTime.plus(2, ChronoUnit.HOURS).toInstant();
    }
}
