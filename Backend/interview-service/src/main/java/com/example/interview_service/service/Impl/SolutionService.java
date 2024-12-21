package com.example.interview_service.service.Impl;

import com.example.interview_service.dto.DiscussionDto;
import com.example.interview_service.dto.SolutionDto;
import com.example.interview_service.mapper.SolutionMapper;
import com.example.interview_service.model.Discussion;
import com.example.interview_service.model.Problem;
import com.example.interview_service.model.Solution;
import com.example.interview_service.repository.ProblemRepository;
import com.example.interview_service.repository.SolutionRepository;
import com.example.interview_service.service.ISolutionService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SolutionService implements ISolutionService {
    private final ProblemRepository problemRepository;
    private final SolutionRepository solutionRepository;
    private final SolutionMapper solutionMapper;

    public SolutionService(ProblemRepository problemRepository, SolutionRepository solutionRepository, SolutionMapper solutionMapper){
        this.solutionMapper = solutionMapper;
        this.solutionRepository  = solutionRepository;
        this.problemRepository = problemRepository;
    }
    public SolutionDto insertSolutionForProblem(UUID problemId, SolutionDto solutionDto){
        try {
            Problem problem = problemRepository.findById(problemId)
                    .orElseThrow(() -> new IllegalArgumentException("There was no problem found with id:" + problemId));
            solutionDto.setProblemId(problemId);
            Solution solution = solutionMapper.toEntity(solutionDto);
            solution = solutionRepository.save(solution);
            return solutionMapper.toDto(solution);
        }
        catch(Exception e){
            System.err.println("There was an error in performing insertion of solution" + e);
            return new SolutionDto();
        }
    }

    public String deleteSolutionForProblem(UUID solutionId){
        try {
            Solution solution = solutionRepository.findById(solutionId).orElseThrow(
                    () -> new IllegalArgumentException("There was a problem in fetching solution with id:" + solutionId)
            );
            solutionRepository.delete(solution);
            return "Delete successful";
        }
        catch(Exception e){
            System.err.println("There was an error in performing deletion of solution" + e);
            return "Deletion failed";
        }
    }

    public SolutionDto updateSolutionForProblem(UUID discussionId, String content)
    {
        try{
            Solution solution = solutionRepository.findById(discussionId)
                    .orElseThrow(() -> new IllegalArgumentException("Discussion with Id:" + discussionId + " Not found"));

            solution.setContent(content);
            solution = solutionRepository.save(solution);
            return solutionMapper.toDto(solution);
        }
        catch(Exception e){
            System.err.println("There was an excpetion in handling the update of discussion");
            return new SolutionDto();
        }
    }
}
