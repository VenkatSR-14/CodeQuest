package com.example.interview_service.service.Impl;

import com.example.interview_service.dto.DiscussionDto;
import com.example.interview_service.mapper.DiscussionMapper;
import com.example.interview_service.model.Discussion;
import com.example.interview_service.model.Problem;
import com.example.interview_service.repository.DiscussionRepository;
import com.example.interview_service.repository.ProblemRepository;
import com.example.interview_service.service.IDiscussionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DiscussionService implements IDiscussionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiscussionService.class);

    private final DiscussionRepository discussionRepository;
    private final DiscussionMapper discussionMapper;
    private final ProblemRepository problemRepository;

    public DiscussionService(ProblemRepository problemRepository,
                             DiscussionRepository discussionRepository,
                             DiscussionMapper discussionMapper) {
        this.discussionRepository = discussionRepository;
        this.discussionMapper = discussionMapper;
        this.problemRepository = problemRepository;
    }

    public DiscussionDto insertDiscussionIntoProblem(UUID problemId, @Valid DiscussionDto discussionDto) {
        try {
            Problem problem = problemRepository.findById(problemId)
                    .orElseThrow(() -> new IllegalArgumentException("Problem not found with ID: " + problemId));

            Discussion discussion = discussionMapper.toEntity(discussionDto, problem);
            discussion = discussionRepository.save(discussion);
            return discussionMapper.toDto(discussion);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error inserting discussion: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during discussion insertion", e);
            throw new RuntimeException("Failed to insert discussion", e);
        }
    }

    public String deleteDiscussionFromProblem(UUID discussionId) {
        try {
            Discussion discussion = discussionRepository.findById(discussionId)
                    .orElseThrow(() -> new IllegalArgumentException("Discussion not found with ID: " + discussionId));
            discussionRepository.delete(discussion);
            return "Delete successful";
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error deleting discussion: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during discussion deletion", e);
            throw new RuntimeException("Failed to delete discussion", e);
        }
    }

    public DiscussionDto updateDiscussionForProblem(UUID discussionId, String content) {
        try {
            Discussion discussion = discussionRepository.findById(discussionId)
                    .orElseThrow(() -> new IllegalArgumentException("Discussion not found with ID: " + discussionId));
            discussion.setContent(content);
            discussion = discussionRepository.save(discussion);
            return discussionMapper.toDto(discussion);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error updating discussion: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during discussion update", e);
            throw new RuntimeException("Failed to update discussion", e);
        }
    }
}
