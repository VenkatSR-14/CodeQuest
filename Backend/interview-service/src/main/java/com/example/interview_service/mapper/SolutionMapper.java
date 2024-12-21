package com.example.interview_service.mapper;

import com.example.interview_service.dto.SolutionDto;
import com.example.interview_service.model.Problem;
import com.example.interview_service.model.Solution;
import com.example.interview_service.repository.ProblemRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class SolutionMapper {

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

    public Solution toEntity(SolutionDto solutionDto, Problem problem) {
        if (solutionDto == null || problem == null) {
            throw new IllegalArgumentException("SolutionDto or Problem cannot be null");
        }
        return new Solution(
                null, // Assuming ID is auto-generated
                solutionDto.getAuthor(),
                solutionDto.getContent(),
                problem
        );
    }
}