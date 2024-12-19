package com.example.interview_service.mapper;

import com.example.interview_service.dto.ProblemDto;
import com.example.interview_service.model.Discussion;
import com.example.interview_service.model.Problem;
import com.example.interview_service.model.Solution;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ProblemMapper {

    // Convert Problem (Entity) to ProblemDto
    public static ProblemDto toDto(Problem problem) {
        if (problem == null) {
            return null;
        }

        // Extract discussion IDs and solution IDs
        List<UUID> discussionIds = problem.getDiscussions().stream()
                .map(Discussion::getId)
                .collect(Collectors.toList());

        List<UUID> solutionIds = problem.getSolutions().stream()
                .map(Solution::getId)
                .collect(Collectors.toList());

        // Create and return the DTO
        return new ProblemDto(
                problem.getConstraints(),
                problem.getProblemDescription(),
                problem.getProblemName(),
                problem.getTopic(),
                problem.getDifficulty(),
                discussionIds,
                solutionIds
        );
    }

    // Convert ProblemDto to Problem (Entity)
    public static Problem toEntity(ProblemDto problemDto) {
        if (problemDto == null) {
            return null;
        }

        Problem problem = new Problem();
        problem.setProblemDescription(problemDto.getProblemDescription());
        problem.setProblemName(problemDto.getProblemName());
        problem.setTopic(problemDto.getTopic()); // Corrected this line
        problem.setDifficulty(problemDto.getDifficulty());
        problem.setConstraints(problemDto.getConstraints());

        // Note: Discussions and Solutions are not handled here and should be managed separately
        return problem;
    }
}
