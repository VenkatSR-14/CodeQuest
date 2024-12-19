package com.example.interview_service.repository;

import com.example.interview_service.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, UUID> {
    public List<Problem> findProblemByTopic(String topic);

    @Query(value = "SELECT *  FROM problems where topic = ANY(:topics) ORDER BY RANDOM() LIMIT :n", nativeQuery = true)
    public List<Problem> findNProblemsByTopics(@Param("topics") List<String> topics, @Param("n") int n);

    @Query(value = "SELECT *  FROM problems ORDER BY RANDOM() LIMIT :n", nativeQuery = true)
    public List<Problem> findNProblems(@Param("n") int n);

}
