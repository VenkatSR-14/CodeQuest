package com.dashboardservice.dashboard_service.service.impl;

import com.dashboardservice.dashboard_service.dto.ProblemDto;
import com.dashboardservice.dashboard_service.model.Problem;
import com.dashboardservice.dashboard_service.repository.ProblemRepository;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemService;
import com.dashboardservice.dashboard_service.service.mapper.ProblemMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemServiceImpl implements ProblemService {
    private final ProblemRepository problemRepository;
    private final ProblemMapper problemMapper;

    public ProblemServiceImpl(ProblemRepository problemRepository, ProblemMapper mapper) {
        this.problemRepository = problemRepository;
        this.problemMapper = mapper;
    }

    @Override
    public List<ProblemDto> getAllProblems() {
        List<Problem> problems = problemRepository.findAll();
        return problems.stream()
                .map(problemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProblemDto> getAllProblemsBelongingToTopics(List<String> topicNames, int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        List<Problem> problemList = problemRepository.findProblemByTopicName(topicNames, pageable);
        return problemList.stream()
                .map(problemMapper::toDto)
                .collect(Collectors.toList());
    }
}
