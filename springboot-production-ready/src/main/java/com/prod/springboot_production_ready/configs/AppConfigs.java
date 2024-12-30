package com.prod.springboot_production_ready.configs;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfigs {

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }


}
