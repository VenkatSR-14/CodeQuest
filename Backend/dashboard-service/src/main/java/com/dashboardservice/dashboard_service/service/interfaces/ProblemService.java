package com.dashboardservice.dashboard_service.service.interfaces;

import com.dashboardservice.dashboard_service.dto.ProblemDto;
import com.dashboardservice.dashboard_service.model.Problem;
import com.dashboardservice.dashboard_service.model.ProblemTopic;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProblemService {
    public List<ProblemDto> getAllProblems();

    public List<ProblemDto> getAllProblemsBelongingToTopics(List<String> topicNames, int limit);

}
