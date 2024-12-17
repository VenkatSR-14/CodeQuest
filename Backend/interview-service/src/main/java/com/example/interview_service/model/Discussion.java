package com.example.interview_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "Discussions")
@NoArgsConstructor
@AllArgsConstructor

public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String author;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    // Setter for Problem
    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Problem getProblem(){
        return this.problem;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(){
        this.author = author;
    }
}
