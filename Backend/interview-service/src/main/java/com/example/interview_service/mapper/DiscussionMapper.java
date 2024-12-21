package com.example.interview_service.mapper;
import com.example.interview_service.dto.DiscussionDto;
import com.example.interview_service.model.Discussion;
import com.example.interview_service.model.Problem;
import com.example.interview_service.repository.ProblemRepository;

import java.util.UUID;

public class DiscussionMapper {

    private final ProblemRepository problemRepository;

    public DiscussionMapper(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public DiscussionDto toDto(Discussion discussion) {
        if (discussion == null) {
            return null;
        }
        return new DiscussionDto(
                discussion.getProblem().getId(),
                discussion.getContent(),
                discussion.getAuthor()
        );
    }

    public Discussion toEntity(DiscussionDto discussionDto) {
        if (discussionDto == null) {
            return null;
        }
        UUID problemId = discussionDto.getProblemId();

        // Use problemRepository here
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new IllegalArgumentException("Problem not found with id: " + problemId));

        return new Discussion(
                null, // Assuming ID is auto-generated
                discussionDto.getAuthor(),
                discussionDto.getContent(),
                problem
        );
    }
}
