package com.securityApp.SpringSecurity.services;

import com.securityApp.SpringSecurity.dto.LoginDto;
import com.securityApp.SpringSecurity.entities.User;
import com.securityApp.SpringSecurity.services.impl.JWTService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    public String login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )


        );
        User user =(User)authentication.getPrincipal();

        String token= jwtService.generateToken(user);

        Cookie cookie=new Cookie("token",token);

        httpServletResponse.addCookie(cookie);

        return token;




    }

}
