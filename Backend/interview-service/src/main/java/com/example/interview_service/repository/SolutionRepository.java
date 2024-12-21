package com.example.interview_service.repository;

import com.example.interview_service.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SolutionRepository extends JpaRepository<Solution, UUID> {
}
