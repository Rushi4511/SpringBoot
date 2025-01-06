package com.securityApp.SpringSecurity.controllers;


import com.securityApp.SpringSecurity.dto.LoginDto;
import com.securityApp.SpringSecurity.dto.SignUpDto;
import com.securityApp.SpringSecurity.dto.UserDto;
import com.securityApp.SpringSecurity.entities.User;
import com.securityApp.SpringSecurity.services.AuthService;
import com.securityApp.SpringSecurity.services.UserService;
import com.securityApp.SpringSecurity.services.impl.JWTService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto){

        UserDto userDto =userService.signUp(signUpDto);

        return ResponseEntity.ok(userDto);

    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){

        String token =authService.login(loginDto,httpServletResponse);
        return ResponseEntity.ok(token);
  }




}
