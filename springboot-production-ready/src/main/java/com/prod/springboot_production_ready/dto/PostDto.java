package com.prod.springboot_production_ready.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Long id;


    private String title;

    private String description;
}
