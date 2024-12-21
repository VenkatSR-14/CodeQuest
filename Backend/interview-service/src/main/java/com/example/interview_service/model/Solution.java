package com.example.interview_service.model;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

@Entity(name = "solutions")
@EqualsAndHashCode(of = "id")
@ToString
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "problem_id", nullable = false)
    private Problem problem;

    public Solution() {
    }

    public Solution(UUID id, String author, String content, Problem problem) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.problem = problem;
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
