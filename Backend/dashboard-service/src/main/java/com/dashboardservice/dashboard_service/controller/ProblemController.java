package com.dashboardservice.dashboard_service.controller;

import com.dashboardservice.dashboard_service.dto.ProblemDto;
import com.dashboardservice.dashboard_service.dto.ProblemTopicDto;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemService;
import com.dashboardservice.dashboard_service.service.interfaces.ProblemTopicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ProblemDto> getAllProblems(){
        return problemService.getAllProblems();
    }

    @GetMapping("/by-topics")
    public List<ProblemDto> getAllProblemsBelongingToTopics(
            @RequestParam(name = "topics") String topics,
            @RequestParam(name = "limit", required = false, defaultValue = "5") int limit

    ){
        if (topics == null || topics.isEmpty()) {
            List<ProblemTopicDto> problemTopics = problemTopicService.getAllProblemTopics();
            if (problemTopics.isEmpty()) {
                return List.of(); // Return an empty list if no topics are available
            }
            List<String> topicList = problemTopics.stream()
                    .map(ProblemTopicDto::getProblemTopicName) // Ensure field matches the DTO definition
                    .collect(Collectors.toList());
            return problemService.getAllProblemsBelongingToTopics(topicList, limit);
        }
        // Else give chosen problems
        List<String> topicList = Arrays.asList(topics.split(","));
        return problemService.getAllProblemsBelongingToTopics(topicList, limit);
    }
}
