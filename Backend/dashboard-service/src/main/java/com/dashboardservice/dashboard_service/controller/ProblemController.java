package com.dashboardservice.dashboard_service.controller;

import com.dashboardservice.dashboard_service.dto.ProblemDto;
import com.dashboardservice.dashboard_service.dto.ProblemTopicDto;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemService;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemTopicService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {
    private final ProblemService problemService;
    private final ProblemTopicService problemTopicService;

    public ProblemController(ProblemService problemService, ProblemTopicService problemTopicService){
        this.problemService = problemService;
        this.problemTopicService = problemTopicService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ProblemDto>> getAllProblems(
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "5") @Min(1) int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProblemDto> problems = problemService.fetchAllProblems(pageable);
        if (problems.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(problems);
    }

    @GetMapping("/by-topics")
    public ResponseEntity<List<ProblemDto>> getAllProblemsBelongingToTopics(
            @RequestParam(name = "topics") String topics,
            @RequestParam(name = "limit", required = false, defaultValue = "5") int limit
    ) {
        List<ProblemDto> problems = problemService.fetchProblemsByTopicNames(
                topics == null || topics.isEmpty()
                        ? problemTopicService.getAllProblemTopics().stream()
                        .map(ProblemTopicDto::getProblemTopicName)
                        .collect(Collectors.toList())
                        : Arrays.asList(topics.split(",")),
                limit
        );
        if (problems.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(problems);
    }

    @PostMapping("/insert")
    public ResponseEntity<ProblemDto> insertProblem(@RequestBody @Valid ProblemDto problemDto){
        ProblemDto response = problemService.createProblem(problemDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
