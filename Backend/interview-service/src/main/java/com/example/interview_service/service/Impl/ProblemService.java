package com.example.interview_service.service.Impl;

import com.example.interview_service.dto.ProblemDto;
import com.example.interview_service.mapper.ProblemMapper;
import com.example.interview_service.model.Problem;
import com.example.interview_service.repository.ProblemRepository;
import com.example.interview_service.service.IProblemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemService implements IProblemService {

    private final ProblemRepository problemRepository;
    private final ProblemMapper problemMapper;

    public ProblemService(ProblemRepository problemRepository, ProblemMapper problemMapper){
        this.problemRepository = problemRepository;
        this.problemMapper = problemMapper;
    }
    public List<ProblemDto> getAllProblems(){
        List<Problem> allProblems = problemRepository.findAll();
        return allProblems.stream()
                .map(ProblemMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProblemDto> getNProblems(int n){
        List<Problem> nProblems = problemRepository.findNProblems(n);
        return nProblems.stream()
                .map(ProblemMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ProblemDto> getProblemsByTopics(List<String> topics, int n){
        List<Problem> nProblemsPerTopics = problemRepository.findNProblemsByTopics(topics, n);
        return nProblemsPerTopics.stream()
                .map(ProblemMapper::toDto)
                .collect(Collectors.toList());
    }
}
