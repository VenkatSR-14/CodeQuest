package com.example.interview_service.service;

import com.example.interview_service.dto.DiscussionDto;
import com.example.interview_service.dto.SolutionDto;

import java.util.UUID;

public interface ISolutionService {
    public SolutionDto insertSolutionForProblem(UUID problemId, SolutionDto solutionDto);

    public String deleteSolutionForProblem(UUID solutionId);

    public SolutionDto updateSolutionForProblem(UUID discussionId, String content);
}
