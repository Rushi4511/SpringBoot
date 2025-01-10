package com.securityApp.SpringSecurity.dto;

import com.securityApp.SpringSecurity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {


    private Long id;
    private String email;
    private String name;

    private Set<Role> roles;

}
