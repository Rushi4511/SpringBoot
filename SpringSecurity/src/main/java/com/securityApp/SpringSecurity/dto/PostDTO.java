package com.securityApp.SpringSecurity.dto;

import com.securityApp.SpringSecurity.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String description;
    private UserDto author;
}
