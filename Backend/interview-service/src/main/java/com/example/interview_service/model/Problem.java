package com.example.interview_service.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "problems") // Table name should be lowercase as a best practice
public class Problem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String difficulty;

    private String topic;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String constraints;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Discussion> discussions = new ArrayList<>();

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solution> solutions = new ArrayList<>();

    // Getters and Setters

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public List<Discussion> getDiscussions() {
        return discussions;
    }

    public void setDiscussions(List<Discussion> discussions) {
        this.discussions = discussions;
    }

    public List<Solution> getSolutions() {
        return solutions;
    }

    public void setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
    }

    // Utility methods for managing bidirectional relationships

    public void addDiscussion(Discussion discussion) {
        if (!discussions.contains(discussion)) {
            discussions.add(discussion);
            discussion.setProblem(this);
        }
    }

    public void removeDiscussion(Discussion discussion) {
        if (discussions.contains(discussion)) {
            discussions.remove(discussion);
            discussion.setProblem(null);
        }
    }

    public void addSolution(Solution solution) {
        if (!solutions.contains(solution)) {
            solutions.add(solution);
            solution.setProblem(this);
        }
    }

    public void removeSolution(Solution solution) {
        if (solutions.contains(solution)) {
            solutions.remove(solution);
            solution.setProblem(null);
        }
    }
}
