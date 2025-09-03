package com.hallak.shared_libraries.dtos.config.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigUtils {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
