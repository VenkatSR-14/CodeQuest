package com.example.interview_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Problems")
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
    private List<Discussion> discussions;

    public void addDiscussion(Discussion discussion){
        discussions.add(discussion);
        discussion.setProblem(this);
    }

    public void removeDiscussion(Discussion discussion){
        discussions.remove(discussion);
        discussion.setProblem(null);
    }

}
