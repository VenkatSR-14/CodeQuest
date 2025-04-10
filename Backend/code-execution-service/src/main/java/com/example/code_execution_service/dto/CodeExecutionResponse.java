package com.example.code_execution_service.dto;

import java.util.List;
import java.util.Map;

public class CodeExecutionResponse {
    private boolean success;
    private String output;
    private String error;
    private long executionTime;
    private Map<String, TestCaseResult> testResults;

    // Default constructor
    public CodeExecutionResponse() {
    }

    // Constructor with all fields
    public CodeExecutionResponse(boolean success, String output, String error, long executionTime, Map<String, TestCaseResult> testResults) {
        this.success = success;
        this.output = output;
        this.error = error;
        this.executionTime = executionTime;
        this.testResults = testResults;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public Map<String, TestCaseResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(Map<String, TestCaseResult> testResults) {
        this.testResults = testResults;
    }

    // Inner class for test case results
    public static class TestCaseResult {
        private boolean passed;
        private String input;
        private String expectedOutput;
        private String actualOutput;
        private String error;

        public TestCaseResult() {
        }

        public TestCaseResult(boolean passed, String input, String expectedOutput, String actualOutput, String error) {
            this.passed = passed;
            this.input = input;
            this.expectedOutput = expectedOutput;
            this.actualOutput = actualOutput;
            this.error = error;
        }

        public boolean isPassed() {
            return passed;
        }

        public void setPassed(boolean passed) {
            this.passed = passed;
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }

        public String getExpectedOutput() {
            return expectedOutput;
        }

        public void setExpectedOutput(String expectedOutput) {
            this.expectedOutput = expectedOutput;
        }

        public String getActualOutput() {
            return actualOutput;
        }

        public void setActualOutput(String actualOutput) {
            this.actualOutput = actualOutput;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
