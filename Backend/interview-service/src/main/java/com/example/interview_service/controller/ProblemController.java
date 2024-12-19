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

    public ProblemController(IProblemService problemService){
        this.problemService = problemService;
    }

    @GetMapping
    public ResponseEntity<List<ProblemDto>> getAllProblems(){
        try {
            List<ProblemDto> allProblems = problemService.getAllProblems();
            return ResponseEntity.ok(allProblems);
        }
        catch (Exception e)
        {
            System.err.println("There was an error in the server" + ": error" + e);
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @GetMapping

    public ResponseEntity<List<ProblemDto>> getNProblems(@RequestBody int n){
        try {
            List<ProblemDto> allProblems = problemService.getNProblems(n);
            return ResponseEntity.ok(allProblems);
        }
        catch(Exception e)
        {
            System.err.println("There was an error in the server" + ": error" + e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
        }
    public ResponseEntity<List<ProblemDto>> getNProblemsWithTopics(@RequestBody int n, @RequestBody List<String> topics){
        try {
            List<ProblemDto> allProblems = problemService.getProblemsByTopics(topics, n);
            return ResponseEntity.ok(allProblems);
        }
        catch(Exception e)
        {
            System.err.println("There was an error in the server" + ": error" + e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
