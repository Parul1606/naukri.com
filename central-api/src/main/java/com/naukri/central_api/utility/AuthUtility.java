package com.naukri.central_api.utility;

import com.naukri.central_api.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class AuthUtility {

    @Value("${secret.password}")
    String secretPassword;

    Long expirationTime = 10000000L;

    UserService userService;

    @Autowired
    public AuthUtility(UserService userService){
        this.userService = userService;
    }

    public String generateJwtToken(String email,
                                String password,
                                String role){

        String payload = email + ":" + password + ":" + role;
        String jwtToken = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, secretPassword)
                .setSubject(payload)
                .compact();
        return jwtToken;
    }

    public String decryptJwtToken(String token){
        String payload = Jwts.parser().setSigningKey(secretPassword)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return payload;
    }

    public boolean validateToken(String token){   //filter will call out validate token to verify if it valid or not
        String payload = this.decryptJwtToken(token);    //payload or credentials are same
        String [] details = payload.split(":");
        String email = details[0];
        String password = details[1];
        String role = details[2];
        // i want to validate email and password is it belonging to correct user or not
        // auth utility is going to call UserService to validate email and password belongs to correct user or not
        return userService.validateCredentials(email, password);
    }
}




/**
 * Working flow of auth api
 * when an api endpoint is hit then it goes to auth filter -> auth filter asks to auth configuration if this endpoint is valid or not..
 * if the endpoint is not secured it will be returned to auth filter and auth filter will direct to the controller that it's not valid
 * if it valid then auth-config will call -> auth-utility -> then if it is valid then auth-utility will generate token -> then it will return to auth-filter with the process and will return to controller.
 * */