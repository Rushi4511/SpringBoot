package com.securityApp.SpringSecurity.dto;

import com.securityApp.SpringSecurity.enums.Permission;
import com.securityApp.SpringSecurity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private String email;
    private String password;
    private String name;
    private Set<Role> roles;
//    private Set<Permission> permissions;
}
