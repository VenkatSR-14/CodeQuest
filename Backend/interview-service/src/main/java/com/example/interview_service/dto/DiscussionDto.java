package com.example.interview_service.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class DiscussionDto {

    @NotNull
    private String author;

    @NotNull
    private String content;

    @NotNull
    private UUID problemId;

    public DiscussionDto(){
        // No args constructor
    }

    public DiscussionDto(UUID problemId, String content, String author){
        this.problemId = problemId;
        this.content = content;
        this.author = author;
    }

    // Getters
    public UUID getProblemId(){
        return this.problemId;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getContent(){
        return this.content;
    }

    // Setters
    public void setContent(String content){
        this.content = content;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setProblemId(UUID id){
        this.problemId =id;
    }
}
