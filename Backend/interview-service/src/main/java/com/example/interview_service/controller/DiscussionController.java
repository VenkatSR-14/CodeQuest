package com.example.interview_service.controller;

import com.example.interview_service.dto.DiscussionDto;
import com.example.interview_service.model.Discussion;
import com.example.interview_service.service.IDiscussionService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/discussions")
public class DiscussionController {
    private final IDiscussionService discussionService;

    public DiscussionController(IDiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    @PostMapping("/{problemId}")
    public ResponseEntity<DiscussionDto> addDiscussionToProblem(
            @PathVariable UUID problemId,
            @Valid @RequestBody DiscussionDto discussionDto
    )
    {
        DiscussionDto createDiscussion = discussionService.insertDiscussionIntoProblem(problemId, discussionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createDiscussion);
    }

    @DeleteMapping("/{discussionId}")
    public ResponseEntity<String> deleteDiscussion(@PathVariable UUID discussionId){
        String result = discussionService.deleteDiscussionFromProblem((discussionId));
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{discussionId}")
    public ResponseEntity<DiscussionDto> updateDiscussion(
            @PathVariable UUID discussionId,
            @RequestBody String content
    ){
        DiscussionDto updatedDiscussion = discussionService.updateDiscussionForProblem(discussionId, content);
        return ResponseEntity.ok(updatedDiscussion);
    }
}



