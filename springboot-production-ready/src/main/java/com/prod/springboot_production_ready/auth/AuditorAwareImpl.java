package com.prod.springboot_production_ready.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {


    //Get Security context
    //Get Authentication
    //Get the Principle
    //Get the Username

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("Rushi Bhaiya");
    }
}
