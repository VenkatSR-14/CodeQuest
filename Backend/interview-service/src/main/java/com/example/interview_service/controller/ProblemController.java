package com.example.interview_service.controller;

import com.example.interview_service.dto.ProblemDto;
import com.example.interview_service.service.IProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/problems") // Base path for the endpoints
@RequiredArgsConstructor
public class ProblemController {

    private final IProblemService problemService;

    /**
     * Fetch N random problems.
     * If 'n' is not provided, it defaults to 5.
     *
     * Example URL:
     * GET /problems/random?n=5
     */
    @GetMapping("/random")
    public CompletableFuture<List<ProblemDto>> getRandomProblems(
            @RequestParam(defaultValue = "5") int n // Default to 5 if 'n' is not passed
    ) {
        return problemService.getRandomProblems(n);
    }

    /**
     * Fetch N problems filtered by topics.
     * If 'topics' is not provided, it can be handled appropriately in the service.
     *
     * Example URL:
     * GET /problems/topics?n=3&topics=math,arrays
     */
    @GetMapping("/topics")
    public CompletableFuture<List<ProblemDto>> getProblemsByTopics(
            @RequestParam(defaultValue = "5") int n, // Default to 5 if 'n' is not passed
            @RequestParam List<String> topics       // List of topics as query parameter
    ) {
        return problemService.getProblemsByTopics(n, topics);
    }
}
