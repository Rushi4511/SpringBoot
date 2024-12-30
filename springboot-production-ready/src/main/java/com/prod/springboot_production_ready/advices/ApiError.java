package com.prod.springboot_production_ready.advices;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {

    private HttpStatus httpStatus;

    private String message;

    private List<String> subErrors;
}
