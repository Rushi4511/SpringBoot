package com.week2.MVC.and.REST.API.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfigs {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }
}
