package com.ssafy.happyhouse.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseConfig {
    @Bean
    ModelMapper mapper(){return new ModelMapper();}
}
