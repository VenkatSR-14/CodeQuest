package com.example.interview_service.service.impl;

import com.example.interview_service.dto.ProblemDto;
import com.example.interview_service.mapper.ProblemMapper;
import com.example.interview_service.model.Problem;
import com.example.interview_service.repository.ProblemRepository;
import com.example.interview_service.service.IProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements IProblemService {
    private final ProblemRepository problemRepository;
    private final ProblemMapper problemMapper;

    @Override
    @Async
    public CompletableFuture<List<Problem>> getAllProblems(){
        List<Problem> problems = problemRepository.findAll();
        return CompletableFuture.completedFuture(problems);
    }

    @Override
    @Async
    public CompletableFuture<List<ProblemDto>> getRandomProblems(int n){
        return this.getAllProblems()
                .thenApply(problems -> {
                    return new Random()
                            .ints(0, problems.size())
                            .distinct()
                            .limit(n)
                            .mapToObj(problems::get)
                            .map(problemMapper::problemToProblemDto) //MapToDto
                            .collect(Collectors.toList());
                });
    }

    public CompletableFuture<List<ProblemDto>> getProblemsByTopics(int n, List<String> topics){
        List<Problem> problems = problemRepository.findByTopicIn(topics);
        List<ProblemDto> result = problems.stream()
                .limit(n)
                .map(problemMapper::problemToProblemDto)
                .collect(Collectors.toList());
        return CompletableFuture.completedFuture(result);


    }
}
