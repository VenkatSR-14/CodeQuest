package com.example.interview_service.repository;

import com.example.interview_service.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProblemRepository extends JpaRepository<Problem, UUID> {
    List<Problem> findByTopicIn(List<String> topics);
}
