package com.dashboardservice.dashboard_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDto {

    @NotNull(message = "Problem name cannot be null")
    private String problemName;

    @NotNull(message = "Problem description name cannot be null")
    private String description; // Fixed naming convention to camelCase

    @NotNull(message = "Problem difficulty be null")
    private String difficulty; // Fixed naming convention to camelCase

    private int numUsersSolved;

    private int numSubmissions;

    private String problemTopicId; // Reference to ProblemTopic's ID as a String or UUID
    private String problemTopicName;
}
