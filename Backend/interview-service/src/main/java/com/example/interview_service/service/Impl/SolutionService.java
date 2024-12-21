package com.example.interview_service.service.Impl;

import com.example.interview_service.dto.SolutionDto;
import com.example.interview_service.mapper.SolutionMapper;
import com.example.interview_service.model.Problem;
import com.example.interview_service.model.Solution;
import com.example.interview_service.repository.ProblemRepository;
import com.example.interview_service.repository.SolutionRepository;
import com.example.interview_service.service.ISolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SolutionService implements ISolutionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SolutionService.class);

    private final ProblemRepository problemRepository;
    private final SolutionRepository solutionRepository;
    private final SolutionMapper solutionMapper;

    public SolutionService(ProblemRepository problemRepository,
                           SolutionRepository solutionRepository,
                           SolutionMapper solutionMapper) {
        this.problemRepository = problemRepository;
        this.solutionRepository = solutionRepository;
        this.solutionMapper = solutionMapper;
    }

    @Override
    @Transactional
    public SolutionDto insertSolutionForProblem(UUID problemId, SolutionDto solutionDto) {
        try {
            Problem problem = problemRepository.findById(problemId)
                    .orElseThrow(() -> new IllegalArgumentException("Problem not found with ID: " + problemId));
            Solution solution = solutionMapper.toEntity(solutionDto, problem);
            solution = solutionRepository.save(solution);
            return solutionMapper.toDto(solution);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid input: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during solution insertion", e);
            throw new RuntimeException("Failed to insert solution", e);
        }
    }

    @Override
    @Transactional
    public String deleteSolutionForProblem(UUID solutionId) {
        try {
            Solution solution = solutionRepository.findById(solutionId)
                    .orElseThrow(() -> new IllegalArgumentException("Solution not found with ID: " + solutionId));
            solutionRepository.delete(solution);
            return "Delete successful";
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error during solution deletion: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during solution deletion", e);
            throw new RuntimeException("Failed to delete solution", e);
        }
    }

    @Override
    @Transactional
    public SolutionDto updateSolutionForProblem(UUID solutionId, String content) {
        try {
            Solution solution = solutionRepository.findById(solutionId)
                    .orElseThrow(() -> new IllegalArgumentException("Solution not found with ID: " + solutionId));
            solution.setContent(content);
            solution = solutionRepository.save(solution);
            return solutionMapper.toDto(solution);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Error during solution update: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during solution update", e);
            throw new RuntimeException("Failed to update solution", e);
        }
    }
}
