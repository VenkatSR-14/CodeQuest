package com.example.interview_service.controller;


import com.example.interview_service.dto.SolutionDto;
import com.example.interview_service.model.Solution;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {
    @GetMapping("/{problemId}")
    public ResponseEntity<List<SolutionDto>> getSolutionsForProblem(){
        return ResponseEntity.ok(new ArrayList<>());
    }
}
