package com.example.interview_service.dto;

import com.example.interview_service.model.Problem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolutionsDto {


    private String author;

    private String content;

    private UUID problemId;
}
