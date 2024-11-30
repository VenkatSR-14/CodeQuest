package com.dashboardservice.dashboard_service.controller;

import com.dashboardservice.dashboard_service.dto.ProblemTopicDto;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemTopicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
