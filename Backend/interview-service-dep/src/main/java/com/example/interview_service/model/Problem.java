package com.example.interview_service.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String topic;

    @Column(columnDefinition = "TEXT")
    private String constraints;

    private String difficulty;

    @Column(columnDefinition = "TEXT")
    private String problemDescription;

    @Column(columnDefinition = "TEXT")
    private String problemName;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discussion> discussions = new ArrayList<>();

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solution> solutions = new ArrayList<>();

    // No args constructor
    public Problem() {

    }

    // ALl args constructor
    public Problem(List<Solution> solutions, List<Discussion> discussions, UUID id, String difficulty,
            String constraints,
            String problemDescription, String problemName, String topic) {
        this.solutions = solutions;
        this.discussions = discussions;
        this.id = id;
        this.difficulty = difficulty;
        this.topic = topic;
        this.problemDescription = problemDescription;
        this.problemName = problemName;
        this.constraints = constraints;
    }

    // Getters
    public UUID getId() {
        return this.id;
    }

    public String getTopic() {
        return this.topic;
    }

    public String getProblemName() {
        return this.problemName;
    }

    public String getProblemDescription() {
        return this.problemDescription;
    }

    public String getConstraints() {
        return this.constraints;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    // Setters

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public void addDiscussion(Discussion discussion) {
        this.discussions.add(discussion);
        discussion.setProblem(this);
    }

    public void addSolution(Solution solution) {
        this.solutions.add(solution);
        solution.setProblem(this);
    }

    public void removeSolution(Solution solution) {
        this.solutions.remove(solution);
        solution.setProblem(null);
    }

    public void removeDiscussion(Discussion discussion) {
        this.discussions.remove(discussion);
        discussion.setProblem(null);
    }

}
