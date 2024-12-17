package com.example.interview_service.service;

import com.example.interview_service.dto.ProblemDto;
import com.example.interview_service.model.Problem;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IProblemService {
    public CompletableFuture<List<ProblemDto>> getRandomProblems(int n);

    public CompletableFuture<List<ProblemDto>> getProblemsByTopics(int n, List<String> topics);

    public CompletableFuture<List<Problem>> getAllProblems();
}
