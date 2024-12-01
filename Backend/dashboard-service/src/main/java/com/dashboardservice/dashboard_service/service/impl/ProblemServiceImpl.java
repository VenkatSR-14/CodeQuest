package com.dashboardservice.dashboard_service.service.impl;

import com.dashboardservice.dashboard_service.dto.ProblemDto;
import com.dashboardservice.dashboard_service.exception.ResourceNotFoundException;
import com.dashboardservice.dashboard_service.model.Problem;
import com.dashboardservice.dashboard_service.model.ProblemTopic;
import com.dashboardservice.dashboard_service.repository.ProblemRepository;
import com.dashboardservice.dashboard_service.repository.ProblemTopicRepository;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemService;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemTopicService;
import com.dashboardservice.dashboard_service.service.mapper.ProblemMapper;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemServiceImpl implements ProblemService {
    private final ProblemRepository problemRepository;
    private final ProblemTopicRepository problemTopicRepository;
    private final ProblemMapper problemMapper;

    public ProblemServiceImpl(ProblemRepository problemRepository, ProblemTopicRepository problemTopicRepository, ProblemMapper mapper) {
        this.problemRepository = problemRepository;
        this.problemTopicRepository = problemTopicRepository;
        this.problemMapper = mapper;
    }

    @Override
    public Page<ProblemDto> fetchAllProblems(Pageable pageable) {
        Page<Problem> problemsPage = problemRepository.findAll(pageable);
        return problemsPage.map(problemMapper::toDto);

    }

    @Override
    public List<ProblemDto> fetchProblemsByTopicNames(List<String> topicNames, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        Page<Problem> problemsPage = problemRepository.findProblemsByTopicNames(topicNames, pageable);
        if (problemsPage.isEmpty()){
            throw new ResourceNotFoundException("No problems found for the given topic names");
        }
        return problemsPage.getContent().stream().map(problemMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ProblemDto createProblem(ProblemDto problemDto) {
        // Find ProblemTopic by name
        ProblemTopic problemTopic = problemTopicRepository.findByProblemTopicName(problemDto.getProblemTopicName())
                .orElseThrow(() -> new ResourceNotFoundException("ProblemTopic with name " + problemDto.getProblemTopicName() + " not found"));

        // Map the DTO to the entity using the resolved ProblemTopic
        Problem problem = problemMapper.toEntity(problemDto, problemTopic);

        // Save and return the response
        Problem savedProblem = problemRepository.save(problem);
        return problemMapper.toDto(savedProblem);
    }

}
