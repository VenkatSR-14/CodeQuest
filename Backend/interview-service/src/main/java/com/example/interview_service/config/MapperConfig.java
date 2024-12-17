package com.example.interview_service.config;

import com.example.interview_service.mapper.ProblemMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.mapstruct.factory.Mappers;

@Configuration
public class MapperConfig {

    @Bean
    public ProblemMapper problemMapper() {
        return Mappers.getMapper(ProblemMapper.class);
    }
}
