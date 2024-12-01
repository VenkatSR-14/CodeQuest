package com.dashboardservice.dashboard_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProblemTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Use AUTO for UUID generation
    private UUID id;

    @Column(nullable = false)
    private String problemTopicName;

    @Column(nullable = false)
    private String problemTopicDescription;

    @OneToMany(mappedBy = "problemTopic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Problem> problemsList;
}
