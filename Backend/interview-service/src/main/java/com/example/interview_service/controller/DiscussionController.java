package com.example.interview_service.controller;

import com.example.interview_service.dto.DiscussionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/discussions")
public class DiscussionController {
    @PostMapping("/{problemId}")
    public ResponseEntity<DiscussionDto> insertDiscussionForProblem(@PathVariable("problemId") int id, @RequestBody DiscussionDto discussionDto){
        // Business logic goes here
        try {

            return ResponseEntity.ok(discussionDto);
        }
    }
}

