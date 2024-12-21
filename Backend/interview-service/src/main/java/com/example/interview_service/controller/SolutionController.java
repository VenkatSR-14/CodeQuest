package com.example.interview_service.controller;

import com.example.interview_service.dto.SolutionDto;
import com.example.interview_service.service.ISolutionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {

    private final ISolutionService solutionService;

    public SolutionController(ISolutionService solutionService) {
        this.solutionService = solutionService;
    }

    /**
     * Adds a solution to a specific problem.
     *
     * @param problemId   UUID of the problem to which the solution is added.
     * @param solutionDto The solution details.
     * @return Created solution as a SolutionDto.
     */
    @PostMapping("/{problemId}")
    public ResponseEntity<SolutionDto> addSolutionToProblem(
            @PathVariable UUID problemId,
            @Valid @RequestBody SolutionDto solutionDto) {
        SolutionDto solutionObject = solutionService.insertSolutionForProblem(problemId, solutionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(solutionObject);
    }

    /**
     * Updates the content of a specific solution.
     *
     * @param solutionId UUID of the solution to update.
     * @param content    New content for the solution.
     * @return Updated solution as a SolutionDto.
     */
    @PutMapping("/{solutionId}")
    public ResponseEntity<SolutionDto> updateSolutionForProblem(
            @PathVariable UUID solutionId,
            @RequestBody String content) {
        SolutionDto solutionDto = solutionService.updateSolutionForProblem(solutionId, content);
        return ResponseEntity.ok(solutionDto);
    }

    /**
     * Deletes a specific solution.
     *
     * @param solutionId UUID of the solution to delete.
     * @return Success message as a string.
     */
    @DeleteMapping("/{solutionId}")
    public ResponseEntity<String> deleteSolutionForProblem(@PathVariable UUID solutionId) {
        String deleteResponse = solutionService.deleteSolutionForProblem(solutionId);
        return ResponseEntity.ok(deleteResponse);
    }
}
