package com.example.interview_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class SolutionsDto {

    private String author;
    private String content;
    private UUID problemId;

    // Add the all-args constructor manually
    public SolutionsDto(String author, String content, UUID problemId) {
        this.author = author;
        this.content = content;
        this.problemId = problemId;
    }
}
