package com.example.interview_service.service;

import com.example.interview_service.dto.ProblemDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProblemService {
    public List<ProblemDto> getAllProblems();
    public List<ProblemDto> getProblemsByTopics(List<String> topic, int n);

    public List<ProblemDto> getNProblems(int n);


}
