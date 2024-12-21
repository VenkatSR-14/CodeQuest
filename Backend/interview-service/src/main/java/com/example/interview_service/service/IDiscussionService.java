package com.example.interview_service.service;

import com.example.interview_service.dto.DiscussionDto;

import java.util.UUID;

public interface IDiscussionService {
    public DiscussionDto insertDiscussionIntoProblem(UUID problemId, DiscussionDto discussionDto);

    public String deleteDiscussionFromProblem(UUID discussionId);

    public DiscussionDto updateDiscussionForProblem(UUID discussionId, String content);
}
