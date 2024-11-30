package com.dashboardservice.dashboard_service.controller;

import com.dashboardservice.dashboard_service.dto.ProblemDto;
import com.dashboardservice.dashboard_service.service.impl.ProblemServiceImpl;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class ProblemController {
    private final ProblemServiceImpl problemService;

    public ProblemController(ProblemServiceImpl problemService){
        this.problemService = problemService;
    }

    @GetMapping
    public List<ProblemDto> getAllProblems(){
        return problemService.getAllProblems();
    }

    @GetMapping()
}
