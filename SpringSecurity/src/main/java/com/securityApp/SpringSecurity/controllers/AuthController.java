package com.securityApp.SpringSecurity.controllers;


import com.securityApp.SpringSecurity.dto.LoginDto;
import com.securityApp.SpringSecurity.dto.LoginResponseDto;
import com.securityApp.SpringSecurity.dto.SignUpDto;
import com.securityApp.SpringSecurity.dto.UserDto;
import com.securityApp.SpringSecurity.entities.User;
import com.securityApp.SpringSecurity.services.AuthService;
import com.securityApp.SpringSecurity.services.UserService;
import com.securityApp.SpringSecurity.services.impl.JWTService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private String deployEnv;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){

        UserDto userDto =userService.signUp(signUpDto);

        return ResponseEntity.ok(userDto);

    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){

        LoginResponseDto loginResponseDto =authService.login(loginDto);

        Cookie cookie =new Cookie("refreshToken",loginResponseDto.getRefreshToken());

        // Adding Security and http only means only Server can access it
        cookie.setHttpOnly(true);

        // Adding Security and making secure in production environment
        cookie.setSecure("production".equals(deployEnv));

        // Adding cookie to the Http Servlet Response
        httpServletResponse.addCookie(cookie);

        // Returning LoginResponseDto

        return ResponseEntity.ok(loginResponseDto);
    }



    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){

        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
//                .map(cookie -> cookie.getValue())
                .map(Cookie ::getValue)
                .orElseThrow(()->new AuthenticationServiceException(
                        "Refresh token not found inside the cookies"
                ));

        LoginResponseDto loginResponseDto =authService.refreshToken(refreshToken);

        return ResponseEntity.ok(loginResponseDto);

    }




}
