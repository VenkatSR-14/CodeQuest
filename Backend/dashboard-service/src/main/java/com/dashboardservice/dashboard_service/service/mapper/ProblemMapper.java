package com.dashboardservice.dashboard_service.service.mapper;

import com.dashboardservice.dashboard_service.dto.ProblemDto;
import com.dashboardservice.dashboard_service.dto.ProblemTopicDto;
import com.dashboardservice.dashboard_service.model.Problem;
import com.dashboardservice.dashboard_service.model.ProblemTopic;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProblemMapper {
    private final ModelMapper modelMapper;

    public ProblemMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public ProblemDto toDto(Problem problem){
        return modelMapper.map(problem, ProblemDto.class);
    }


    public ProblemTopicDto toDto(ProblemTopic problemtopic){
        return modelMapper.map(problemtopic, ProblemTopicDto.class);
    }

    public Problem toEntity(ProblemDto problemDto){
        return modelMapper.map(problemDto, Problem.class);
    }


    public ProblemTopic toEntity(ProblemTopicDto problemTopicDto){
        return modelMapper.map(problemTopicDto, ProblemTopic.class);
    }
}
