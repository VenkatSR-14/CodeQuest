package com.example.interview_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DiscussionsDto {
    private String author;

    private String content;

    private UUID problemId;
}
