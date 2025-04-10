package com.example.code_execution_service.dto;

import java.util.Map;

public class CodeExecutionRequest {
    private String code;
    private String language;
    private Map<String, Object> testCases;
    private Long problemId;

    // Default constructor
    public CodeExecutionRequest() {
    }

    // Constructor with all fields
    public CodeExecutionRequest(String code, String language, Map<String, Object> testCases, Long problemId) {
        this.code = code;
        this.language = language;
        this.testCases = testCases;
        this.problemId = problemId;
    }

    // Getters and Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Map<String, Object> getTestCases() {
        return testCases;
    }

    public void setTestCases(Map<String, Object> testCases) {
        this.testCases = testCases;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }
}
