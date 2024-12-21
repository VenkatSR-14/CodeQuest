package com.example.interview_service.controller;


import com.example.interview_service.dto.SolutionDto;
import com.example.interview_service.model.Solution;
import com.example.interview_service.repository.SolutionRepository;
import com.example.interview_service.service.ISolutionService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {

    private final ISolutionService solutionService;
    private final SolutionRepository solutionRepository;


    public SolutionController(ISolutionService solutionService, SolutionRepository solutionRepository){
        this.solutionService = solutionService;
        this.solutionRepository = solutionRepository;
    }
    @PostMapping("/{problemId}")
    public ResponseEntity<SolutionDto> addSolutionToProblem(@PathVariable UUID problemId,
                                                            @Valid @RequestBody SolutionDto solutionDto){
        SolutionDto solutionObject = solutionService.insertSolutionForProblem(problemId, solutionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(solutionObject);
    }

    @PutMapping("/{solutionId}")
    public ResponseEntity<SolutionDto> updateSolutionToProblem(@PathVariable UUID solutionId, String content){
        SolutionDto solutionDto = solutionService.updateSolutionForProblem(solutionId, content);
        return ResponseEntity.ok(solutionDto);

    }

    @DeleteMapping("/{solutionId}")
    public ResponseEntity<String> deleteSolutionForProblem(@PathVariable UUID solutionId){
        String deleteResponse = solutionService.deleteSolutionForProblem(solutionId);
        return ResponseEntity.ok(deleteResponse);
    }


}
