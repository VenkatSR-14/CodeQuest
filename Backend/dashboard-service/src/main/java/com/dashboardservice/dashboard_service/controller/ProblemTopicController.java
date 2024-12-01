package com.dashboardservice.dashboard_service.controller;

import com.dashboardservice.dashboard_service.dto.ProblemTopicDto;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemTopicService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/problem-topics")
public class ProblemTopicController {
    private final ProblemTopicService problemTopicService;

    public ProblemTopicController(ProblemTopicService problemTopicService) {
        this.problemTopicService = problemTopicService;
    }

    @GetMapping
    public List<ProblemTopicDto> getAllProblemTopics() {
        return problemTopicService.getAllProblemTopics();
    }

    @PostMapping("/insert")
    public ResponseEntity<ProblemTopicDto> InsertProblemTopic(@Valid @RequestBody ProblemTopicDto problemTopicDto){
        ProblemTopicDto response = problemTopicService.insertProblemTopic(problemTopicDto);
        return new ResponseEntity<ProblemTopicDto>(response, HttpStatus.CREATED);
    }
}
