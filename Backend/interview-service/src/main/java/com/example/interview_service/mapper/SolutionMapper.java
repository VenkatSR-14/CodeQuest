package com.example.interview_service.mapper;

import com.example.interview_service.dto.SolutionDto;
import com.example.interview_service.model.Problem;
import com.example.interview_service.model.Solution;
import com.example.interview_service.repository.ProblemRepository;

import java.util.UUID;


public class SolutionMapper {
    private final ProblemRepository problemRepository;

    public SolutionMapper(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public SolutionDto toDto(Solution solution) {
        if (solution == null) {
            return null;
        }
        return new SolutionDto(
                solution.getProblem().getId(),
                solution.getContent(),
                solution.getAuthor()
        );
    }

    public Solution toEntity(SolutionDto solutionDto) {
        if (solutionDto == null) {
            return null;
        }
        UUID problemId = solutionDto.getProblemId();

        // Use problemRepository here
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new IllegalArgumentException("Problem not found with id: " + problemId));

        return new Solution(
                null, // Assuming ID is auto-generated
                solutionDto.getAuthor(),
                solutionDto.getContent(),
                problem
        );
    }
}
