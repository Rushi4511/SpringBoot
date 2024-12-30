package com.prod.springboot_production_ready.entities;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "posts")
public class PostEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    private String description;
}
