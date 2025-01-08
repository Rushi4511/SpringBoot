package com.securityApp.SpringSecurity.services;

import com.securityApp.SpringSecurity.dto.LoginDto;
import com.securityApp.SpringSecurity.dto.LoginResponseDto;
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
    private final UserService userService;


    public LoginResponseDto login(@RequestBody LoginDto loginDto){
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )


        );
        User user =(User)authentication.getPrincipal();

        String accessToken= jwtService.generateAccessToken(user);

        String refreshToken = jwtService.generateRefreshToken(user);



//        Cookie cookie=new Cookie("token",token);
//
//        httpServletResponse.addCookie(cookie);

        return new LoginResponseDto(user.getId(), accessToken,refreshToken);




    }

    public LoginResponseDto refreshToken(String refreshToken) {

        Long userId = jwtService.getUserIdFromToken(refreshToken);

        User user = userService.getUserById(userId);

        String accessToken = jwtService.generateAccessToken(user);

        return new LoginResponseDto(user.getId(),accessToken,refreshToken);

    }
}
