package com.example.interview_service.controller;

import com.example.interview_service.dto.DiscussionDto;
import com.example.interview_service.service.IDiscussionService;
import jakarta.validation.Valid;
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

    /**
     * Adds a discussion to a specific problem.
     *
     * @param problemId     UUID of the problem to which the discussion is added.
     * @param discussionDto The discussion details.
     * @return Created discussion as a DiscussionDto.
     */
    @PostMapping("/{problemId}")
    public ResponseEntity<DiscussionDto> addDiscussionToProblem(
            @PathVariable UUID problemId,
            @Valid @RequestBody DiscussionDto discussionDto
    ) {
        DiscussionDto createdDiscussion = discussionService.insertDiscussionIntoProblem(problemId, discussionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiscussion);
    }

    /**
     * Deletes a specific discussion.
     *
     * @param discussionId UUID of the discussion to delete.
     * @return Success message as a string.
     */
    @DeleteMapping("/{discussionId}")
    public ResponseEntity<String> deleteDiscussion(@PathVariable UUID discussionId) {
        String result = discussionService.deleteDiscussionFromProblem(discussionId);
        return ResponseEntity.ok(result);
    }

    /**
     * Updates the content of a specific discussion.
     *
     * @param discussionId UUID of the discussion to update.
     * @param content      New content for the discussion.
     * @return Updated discussion as a DiscussionDto.
     */
    @PutMapping("/{discussionId}")
    public ResponseEntity<DiscussionDto> updateDiscussion(
            @PathVariable UUID discussionId,
            @RequestBody String content
    ) {
        DiscussionDto updatedDiscussion = discussionService.updateDiscussionForProblem(discussionId, content);
        return ResponseEntity.ok(updatedDiscussion);
    }
}
