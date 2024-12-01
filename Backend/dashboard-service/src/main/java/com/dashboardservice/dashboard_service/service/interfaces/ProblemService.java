package com.dashboardservice.dashboard_service.service.interfaces;

import com.dashboardservice.dashboard_service.dto.ProblemDto;
import com.dashboardservice.dashboard_service.model.Problem;
import com.dashboardservice.dashboard_service.model.ProblemTopic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface ProblemService {
    Page<ProblemDto> fetchAllProblems(Pageable pageable);

    List<ProblemDto> fetchProblemsByTopicNames(List<String> topicNames, int limit);

    ProblemDto createProblem(ProblemDto problemDto);

}
