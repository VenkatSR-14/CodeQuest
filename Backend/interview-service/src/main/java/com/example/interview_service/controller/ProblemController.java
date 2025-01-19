package com.example.interview_service.controller;


import com.example.interview_service.dto.ProblemDto;
import com.example.interview_service.service.IProblemService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {
    private final IProblemService problemService;

    public ProblemController(IProblemService problemService) {
        this.problemService = problemService;
    }

    // Endpoint to get all problems
    @GetMapping
    public ResponseEntity<List<ProblemDto>> getAllProblems() {
        try {
            List<ProblemDto> allProblems = problemService.getAllProblems();
            return ResponseEntity.ok(allProblems);
        } catch (Exception e) {
            System.err.println("There was an error in the server: " + e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // Endpoint to get 'n' problems
    @GetMapping("/count")
    public ResponseEntity<List<ProblemDto>> getNProblems(@RequestBody int n) {
        try {
            List<ProblemDto> nProblems = problemService.getNProblems(n);
            return ResponseEntity.ok(nProblems);
        } catch (Exception e) {
            System.err.println("There was an error in the server: " + e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

    // Endpoint to get 'n' problems with specific topics
    @GetMapping("/topics")
    public ResponseEntity<List<ProblemDto>> getNProblemsWithTopics(@RequestBody int n, @RequestBody List<String> topics) {
        try {
            List<ProblemDto> problemsByTopics = problemService.getProblemsByTopics(topics, n);
            return ResponseEntity.ok(problemsByTopics);
        } catch (Exception e) {
            System.err.println("There was an error in the server: " + e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
