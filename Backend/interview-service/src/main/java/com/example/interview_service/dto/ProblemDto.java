package com.example.interview_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class ProblemDto {
    @NotNull
    @Size(min = 1, max = 255)
    private String topic;

    @NotNull
    @Size(min = 1, max = 255)
    private String difficulty;

    @NotNull
    private String constraints;

    @NotNull
    private String problemDescription;

    @NotNull
    private String problemName;

    private List<UUID> discussionIds;
    private List<UUID> solutionsIds;

    // No-args constructor
    public ProblemDto() {}

    // All-args constructor
    public ProblemDto(String constraints,
                      String problemDescription,
                      String problemName,
                      String topic,
                      String difficulty,
                      List<UUID> discussionIds,
                      List<UUID> solutionsIds) {
        this.constraints = constraints;
        this.problemDescription = problemDescription;
        this.problemName = problemName;
        this.topic = topic;
        this.difficulty = difficulty;
        this.discussionIds = discussionIds;
        this.solutionsIds = solutionsIds;
    }

    // Getters
    public String getTopic() {
        return topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getConstraints() {
        return constraints;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public String getProblemName() {
        return problemName;
    }

    public List<UUID> getDiscussionIds() {
        return discussionIds;
    }

    public List<UUID> getSolutionsIds() {
        return solutionsIds;
    }

    // Setters
    public void setSolutionsIds(List<UUID> solutionsIds) {
        this.solutionsIds = solutionsIds;
    }

    public void setDiscussionIds(List<UUID> discussionIds) {
        this.discussionIds = discussionIds;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    @Override
    public String toString() {
        return "ProblemDto{" +
                "topic='" + topic + '\'' +
                ", constraints='" + constraints + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                ", problemName='" + problemName + '\'' +
                ", discussionIds=" + discussionIds +
                ", solutionsIds=" + solutionsIds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProblemDto that = (ProblemDto) o;
        return Objects.equals(topic, that.topic) &&
                Objects.equals(constraints, that.constraints) &&
                Objects.equals(difficulty, that.difficulty) &&
                Objects.equals(problemDescription, that.problemDescription) &&
                Objects.equals(problemName, that.problemName) &&
                Objects.equals(discussionIds, that.discussionIds) &&
                Objects.equals(solutionsIds, that.solutionsIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topic, constraints, difficulty, problemDescription, problemName, discussionIds, solutionsIds);
    }
}
