package com.example.interview_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemDto {
    private UUID id; // Include this if needed for identification

    private String name;
    private String difficulty;
    private String topic;
    private String description;
    private String constraints;

    // Initialize lists to prevent NullPointerExceptions
    private List<DiscussionsDto> discussions = new ArrayList<>();
    private List<SolutionsDto> solutions = new ArrayList<>();
}
