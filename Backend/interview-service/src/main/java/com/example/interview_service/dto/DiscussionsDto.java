package com.example.interview_service.dto;

import java.util.UUID;

public class DiscussionsDto {

    private String author;
    private String content;
    private UUID problemId;

    public DiscussionsDto() {
        // No-args constructor
    }

    public DiscussionsDto(String author, String content, UUID problemId) {
        this.author = author;
        this.content = content;
        this.problemId = problemId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getProblemId() {
        return problemId;
    }

    public void setProblemId(UUID problemId) {
        this.problemId = problemId;
    }
}
