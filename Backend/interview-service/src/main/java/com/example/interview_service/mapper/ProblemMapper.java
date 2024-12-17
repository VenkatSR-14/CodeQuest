package com.example.interview_service.mapper;

import com.example.interview_service.dto.DiscussionsDto;
import com.example.interview_service.dto.ProblemDto;
import com.example.interview_service.dto.SolutionsDto;
import com.example.interview_service.model.Discussion;
import com.example.interview_service.model.Problem;
import com.example.interview_service.model.Solution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProblemMapper {

    // Map Problem entity to ProblemDto
    @Mapping(target = "discussions", source = "discussions")
    @Mapping(target = "solutions", source = "solutions")
    ProblemDto problemToProblemDto(Problem problem);

    // Map Discussion entity to DiscussionsDto
    @Mapping(target = "problemId", source = "problem.id")
    DiscussionsDto discussionToDiscussionsDto(Discussion discussion);

    // Map Solution entity to SolutionsDto
    @Mapping(target = "problemId", source = "problem.id")
    SolutionsDto solutionToSolutionsDto(Solution solution);

    // Map List<Discussion> to List<DiscussionsDto>
    List<DiscussionsDto> mapDiscussions(List<Discussion> discussions);

    // Map List<Solution> to List<SolutionsDto>
    List<SolutionsDto> mapSolutions(List<Solution> solutions);
}
