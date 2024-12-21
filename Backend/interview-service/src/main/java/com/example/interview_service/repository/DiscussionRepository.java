package com.example.interview_service.repository;

import com.example.interview_service.model.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiscussionRepository extends JpaRepository<Discussion, UUID> {
}
