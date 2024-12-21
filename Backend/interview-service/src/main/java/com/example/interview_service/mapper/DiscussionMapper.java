package com.example.interview_service.mapper;

import com.example.interview_service.dto.DiscussionDto;
import com.example.interview_service.model.Discussion;
import com.example.interview_service.model.Problem;
import org.springframework.stereotype.Component;

@Component
public class DiscussionMapper {

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

    public Discussion toEntity(DiscussionDto discussionDto, Problem problem) {
        if (discussionDto == null || problem == null) {
            throw new IllegalArgumentException("DiscussionDto or Problem cannot be null");
        }

        return new Discussion(
                null, // Assuming ID is auto-generated
                discussionDto.getAuthor(),
                discussionDto.getContent(),
                problem
        );
    }
}
