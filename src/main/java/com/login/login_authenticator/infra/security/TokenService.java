package com.login.login_authenticator.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.login.login_authenticator.user.User;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.shh}")
    private String shh;

    public String tokenGenerator(User user){
        try{
            Algorithm alg = Algorithm.HMAC256(shh);

            String token = JWT.create().withIssuer("login-authenticator")
                    .withSubject(user.getEmail())
                    .withExpiresAt(this.generateExpirationDate())
                    .sign(alg);
            return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error to authenticate");

        }
    }

    public String validateToken(String token){
        try{
            Algorithm alg = Algorithm.HMAC256(shh);
            return JWT.require(alg).withIssuer("login-authenticator")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return null;
        }
    }

    private Instant generateExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
