package com.securityApp.SpringSecurity.services;

import com.securityApp.SpringSecurity.dto.SignUpDto;
import com.securityApp.SpringSecurity.dto.UserDto;
import com.securityApp.SpringSecurity.entities.User;
import com.securityApp.SpringSecurity.exceptions.ResourceNotFoundException;
import com.securityApp.SpringSecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User with email: "+username+" not found"));
    }

    public User getUserById(Long userId){

        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with id: "+userId+" not found"));
    }

    public User getUserByEmail(String email){

        return userRepository.findUserByEmail(email).orElseThrow(()->new ResourceNotFoundException("User with id: "+email+" not found"));
    }

    public UserDto signUp(SignUpDto signUpDto) {

        Optional<User> user=userRepository.findByEmail(signUpDto.getEmail());

        if(user.isPresent()){
            throw new BadCredentialsException("User with email"+signUpDto.getEmail()+"already exists");
        }

        User toBeCreatedUser = modelMapper.map(signUpDto,User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

        User savedUser =userRepository.save(toBeCreatedUser);

        return modelMapper.map(savedUser,UserDto.class);
    }
}
