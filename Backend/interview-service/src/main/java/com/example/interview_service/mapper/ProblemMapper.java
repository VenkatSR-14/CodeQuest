package com.example.interview_service.mapper;


import com.example.interview_service.dto.DiscussionsDto;
import com.example.interview_service.dto.ProblemDto;
import com.example.interview_service.dto.SolutionsDto;
import com.example.interview_service.model.Discussion;
import com.example.interview_service.model.Problem;
import com.example.interview_service.model.Solution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProblemMapper {

    ProblemMapper INSTANCE = Mappers.getMapper(ProblemMapper.class);

    // Map Problem entity to ProblemDto
    @Mapping(target = "discussions", source = "discussions")
    @Mapping(target = "solutions", source = "solutions")
    ProblemDto problemToProblemDto(Problem problem);

    //Map Discussions entity to discussionsDto
    default DiscussionsDto mapDiscussionToDto(Discussion discussion){
        return new DiscussionsDto(
                discussion.getAuthor(),
                discussion.getContent(),
                discussion.getProblem().getId()
        );
    }

    default SolutionsDto mapSolutionToDto(Solution solution){
        return new SolutionsDto(
                solution.getAuthor(),
                solution.getContent(),
                solution.getProblem().getId()
        );
    }

    default List<DiscussionsDto> mapDiscussions(List<Discussion> discussions){
        return discussions.stream().map(
                this::mapDiscussionToDto
        )
                .toList();
    }

    default List<SolutionsDto> mapSolutions(List<Solution> solutions){
        return solutions.stream().map(
                        this::mapSolutionToDto
                )
                .toList();
    }
}

