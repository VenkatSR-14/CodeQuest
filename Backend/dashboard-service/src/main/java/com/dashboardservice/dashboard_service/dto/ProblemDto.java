package com.dashboardservice.dashboard_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDto {

    public ProblemDto(String problemName, String description, String difficulty,
                      int numUsersSolved, int numSubmissions, UUID problemTopicId) {
        this.problemName = problemName;
        this.description = description;
        this.difficulty = difficulty;
        this.numUsersSolved = numUsersSolved;
        this.numSubmissions = numSubmissions;
        this.problemTopicId = problemTopicId;
    }

    @NotNull(message = "Problem name cannot be null")
    private String problemName;

    @NotNull(message = "Problem description name cannot be null")
    private String description; // Fixed naming convention to camelCase

    @NotNull(message = "Problem difficulty be null")
    private String difficulty; // Fixed naming convention to camelCase

    @Min(value = 0, message = "Number of users solved should be atleast 0")
    private int numUsersSolved;
    @Min(value = 0, message = "Number of submissions should be atleast 0")
    private int numSubmissions;


    private UUID problemTopicId; // Reference to ProblemTopic's ID as a String or UUID
    @NotNull(message = "Problem topic ID is required")
    private String problemTopicName;
}
