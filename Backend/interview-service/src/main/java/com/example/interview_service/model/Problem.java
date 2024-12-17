package com.example.interview_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "problems") // Table name should be lowercase as a best practice
@Data
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
    private List<Discussion> discussions = new ArrayList<>(); // Initialize to prevent NullPointerException

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Solution> solutions = new ArrayList<>(); // Initialize to prevent NullPointerException

    // Add discussion to the list
    public void addDiscussion(Discussion discussion) {
        discussions.add(discussion);
        discussion.setProblem(this);
    }

    // Remove discussion from the list
    public void removeDiscussion(Discussion discussion) {
        discussions.remove(discussion);
        discussion.setProblem(null);
    }

    // Add solution to the list
    public void addSolution(Solution solution) {
        solutions.add(solution);
        solution.setProblem(this);
    }

    // Remove solution from the list
    public void removeSolution(Solution solution) {
        solutions.remove(solution);
        solution.setProblem(null);
    }
}
