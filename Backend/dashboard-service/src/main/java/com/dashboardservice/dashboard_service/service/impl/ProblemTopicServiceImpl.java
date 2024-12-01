package com.dashboardservice.dashboard_service.service.impl;

import com.dashboardservice.dashboard_service.dto.ProblemTopicDto;
import com.dashboardservice.dashboard_service.model.ProblemTopic;
import com.dashboardservice.dashboard_service.repository.ProblemTopicRepository;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemTopicService;
import com.dashboardservice.dashboard_service.service.mapper.ProblemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProblemTopicServiceImpl implements ProblemTopicService {
    private final ProblemTopicRepository problemTopicRepository;
    private final ProblemMapper problemMapper;
    public ProblemTopicServiceImpl(ProblemTopicRepository problemTopicRepository, ProblemMapper problemMapper){
        this.problemTopicRepository = problemTopicRepository;
        this.problemMapper = problemMapper;
    }

    // This should return the dto object so we map it using the problemMapper
    public List<ProblemTopicDto> getAllProblemTopics(){
        List<ProblemTopic> problemTopics = problemTopicRepository.findAll();
        return problemTopics.stream()
                .map(problemMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProblemTopicDto insertProblemTopic(ProblemTopicDto problemTopicDto){
        ProblemTopic topic = problemMapper.toEntity(problemTopicDto); // Use mapper here
        log.info("Inserting problem topic: {}", problemTopicDto.getProblemTopicName());
        ProblemTopic savedProblemTopic = problemTopicRepository.save(topic);
        return problemMapper.toDto(savedProblemTopic); // Use mapper here as well
    }

}
