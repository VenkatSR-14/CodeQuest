package com.dashboardservice.dashboard_service.repository;

import com.dashboardservice.dashboard_service.model.Problem;
import com.dashboardservice.dashboard_service.model.ProblemTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, UUID> {
    /**
     * Find a single problem by its name.
     *
     * @param problemName the name of the problem.
     * @return the Problem entity matching the given name.
     */
    Problem findProblemByProblemName(String problemName);
    /**
     * Find problems by topic name with pagination to limit results.
     *
     * @param topicNames the name of the topic to filter by.
     * @param pageable  the Pageable object to limit the number of results.
     * @return a list of Problem entities matching the topic name.
     */
    @Query(value = "SELECT p FROM Problem p WHERE LOWER(p.problemTopic.problemTopicName) IN :topicNames",
            countQuery = "SELECT COUNT(p.id) FROM Problem p WHERE LOWER(p.problemTopic.problemTopicName) IN :topicNames")
    Page<Problem> findProblemsByTopicNames(@Param("topicNames") List<String> topicNames, Pageable pageable);




}
