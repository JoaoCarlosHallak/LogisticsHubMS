package com.hallak.shared_libraries.config.utils;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigUtils {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }



}
