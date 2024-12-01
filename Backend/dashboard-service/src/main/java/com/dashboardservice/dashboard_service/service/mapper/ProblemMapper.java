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

    public Problem toEntity(ProblemDto problemDto, ProblemTopic problemTopic){
        Problem problem = modelMapper.map(problemDto, Problem.class);
        problem.setProblemTopic(problemTopic); // Set the resolved ProblemTopic
        return problem;
    }

    public ProblemTopicDto toDto(ProblemTopic problemTopic){
        return modelMapper.map(problemTopic, ProblemTopicDto.class);
    }

    public ProblemTopic toEntity(ProblemTopicDto problemTopicDto){
        return modelMapper.map(problemTopicDto, ProblemTopic.class);
    }
}