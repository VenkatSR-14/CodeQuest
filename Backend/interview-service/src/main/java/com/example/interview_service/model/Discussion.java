package com.example.interview_service.model;


import jakarta.persistence.*;


import java.util.UUID;

@Entity(name = "discussions")
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

    //All args constructor
    public Discussion(UUID id, String author, String content, Problem problem){
        this.problem = problem;
        this.author = author;
        this.content = content;
        this.id = id;
    }

    // No args constructor
    public Discussion(){

    }
    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id){
        this.id = id;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content  = content;
    }

    public void setProblem(Problem problem){
        this.problem = problem;
    }

    public Problem getProblem(){
        return this.problem;
    }

}
