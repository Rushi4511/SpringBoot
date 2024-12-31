package com.prod.springboot_production_ready.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class EmployeeDTO {


    private Long id;

    private String name;

    private String email;

    private Integer age;

    private String role;

    private Double salary;

    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;
}
