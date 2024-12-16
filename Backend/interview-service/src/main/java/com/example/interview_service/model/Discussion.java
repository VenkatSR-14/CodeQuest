package com.example.interview_service.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Discussions")
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
}
