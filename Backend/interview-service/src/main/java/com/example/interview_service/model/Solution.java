package com.example.interview_service.model;


import jakarta.persistence.*;

import java.util.UUID;

@Entity(name = "solutions")
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(columnDefinition = "TEXT")
    private String author;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    public Solution(){
        //No args constructor
    }

    public Solution(UUID id, String author, String content, Problem problem){
        this.problem = problem;
        this.author = author;
        this.content = content;
        this.id = id;
    }

    // Getters and setters
    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return this.author;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setProblem(Problem problem){
        this.problem = problem;
    }

    public Problem getProblem(){
        return this.problem;
    }

}
