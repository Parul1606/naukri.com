package com.naukri.central_api.security;

import com.naukri.central_api.utility.AuthUtility;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
// OncePerRequestFilter is a Spring-provided abstract class that ensures your filter logic runs only once per HTTP request, even during internal forwards or includes.
public class AuthFilter extends OncePerRequestFilter{

    AuthUtility authUtility;

    @Autowired
    public AuthFilter(AuthUtility authUtility){
        this.authUtility = authUtility;
    }

    /**
     * This method is meant to intercept HTTP requests and decide whether they should proceed or be rejected.

     * It's called automatically by the servlet filter mechanism in Spring Security.

     * It takes 3 parameters:

     * HttpServletRequest request – the incoming HTTP request.

     * HttpServletResponse response – the outgoing response.

     * FilterChain: filterChain – lets you pass the request down the chain to the next filter or resource (like a controller).
     * */
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Inside filter");
        String bearerToken = request.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            String token = bearerToken.substring(7);
            //we got the token now we need to validate this token is a genuine token or not.
            boolean isValid = authUtility.validateToken(token);
            if(isValid == false){
                // I am not going to set any kind of authentication and i will return from here it self
                // before filtering if i am not setting any kind of authentication that
                // means i am rejecting the request
                filterChain.doFilter(request, response);
                return;
            }
            String payload = authUtility.decryptJwtToken(token);

            /**
             * UsernamePasswordAuthenticationToken is a Spring object representing an authenticated user.
             * Arguments:
             * payload – who the user is (e.g., email or username).
             * null – password is not needed here.
             * Collections.emptyList() – no roles/authorities given.
             * This tells Spring Security: “✅ The user is authenticated for this request.”
             * */
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(payload, null, Collections.emptyList());

            /**
             * SecurityContextHolder is a Spring Security class that stores security-related information
             * (like the currently authenticated user) for the current request/thread.
             * getContext() – retrieves the current SecurityContext, which holds authentication information.
             * setAuthentication(authenticationToken) – sets the authenticated user's details in the context,
             * so that Spring Security knows the user is authenticated for this request.
             * This step is essential to allow secure endpoints (with @PreAuthorize, etc.) to recognize the user
             * and give access accordingly.
             * Without setting this, Spring treats the request as anonymous (unauthenticated).
             */
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);  // If you are not setting up username and password authentication that means you are rejecting token
    }

}

/**
 * doFilterInternal
    → Request enters → Extract Authorization header
        → If header starts with Bearer →
            → Get JWT token
            → Validate token
                → ❌ If invalid → Don't authenticate
                → ✅ If valid →
                    → Decrypt token
                    → Set user in Spring Security
    → Pass the request to next filter/controller
*/