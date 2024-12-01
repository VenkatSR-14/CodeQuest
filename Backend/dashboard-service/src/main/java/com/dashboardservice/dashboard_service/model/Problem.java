package com.dashboardservice.dashboard_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Problem {
    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO for UUID generation
    private UUID id;

    @Column(nullable = false)
    private String problemName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String difficulty;

    @Column(nullable = false)
    private int numUsersSolved;

    @Column(nullable = false)
    private int numSubmissions;

    @ManyToOne
    @JoinColumn(name = "problemTopicId", nullable = false, foreignKey = @ForeignKey(name = "fk_problem_topic"))
    private ProblemTopic problemTopic;
}
