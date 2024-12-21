package com.example.interview_service.service.Impl;

import com.example.interview_service.dto.DiscussionDto;
import com.example.interview_service.mapper.DiscussionMapper;
import com.example.interview_service.model.Discussion;
import com.example.interview_service.model.Problem;
import com.example.interview_service.repository.DiscussionRepository;
import com.example.interview_service.repository.ProblemRepository;
import com.example.interview_service.service.IDiscussionService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DiscussionService implements IDiscussionService {
    private final DiscussionRepository discussionRepository;
    private final DiscussionMapper discussionMapper;
    private final ProblemRepository problemRepository;

    public DiscussionService(ProblemRepository problemRepository, DiscussionRepository discussionRepository, DiscussionMapper discussionMapper){
        this.discussionRepository = discussionRepository;
        this.discussionMapper = discussionMapper;
        this.problemRepository = problemRepository;
    }
    public DiscussionDto insertDiscussionIntoProblem(UUID problemId, DiscussionDto discussionDto)
    {
        try{
            Discussion discussion = discussionMapper.toEntity(discussionDto);
            Problem problem = problemRepository.findById(problemId).orElseThrow(
                    () -> new IllegalArgumentException("There was no problem with problemID: " +problemId)
            );
            discussion.setProblem(problem);
            discussion = discussionRepository.save(discussion);
            return discussionMapper.toDto(discussion);

        }
        catch(Exception e){
            System.err.println("There was an error during insertion:" + e);
            return new DiscussionDto();
        }
    }

    public String deleteDiscussionFromProblem(UUID discussionId){
        try{
            Discussion discussion = discussionRepository.findById(discussionId)
                    .orElseThrow(() -> new IllegalArgumentException("Discussion with Id:" + discussionId + " Not found"));
            discussionRepository.delete(discussion);
            return "Delete successful";
        }
        catch(Exception e){
            System.err.println("There was an excpetion in handling the deletion of this discussion");
            return "Delete failed";
        }
    }

    public DiscussionDto updateDiscussionForProblem(UUID discussionId, String content){
        try{
            Discussion discussion = discussionRepository.findById(discussionId)
                    .orElseThrow(() -> new IllegalArgumentException("Discussion with Id:" + discussionId + " Not found"));

            discussion.setContent(content);
            discussion = discussionRepository.save(discussion);
            return discussionMapper.toDto(discussion);
        }
        catch(Exception e){
            System.err.println("There was an excpetion in handling the update of discussion");
            return new DiscussionDto();
        }
    }
}
