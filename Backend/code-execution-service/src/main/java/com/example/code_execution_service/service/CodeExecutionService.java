package com.example.code_execution_service.service;

import com.example.code_execution_service.dto.CodeExecutionRequest;
import com.example.code_execution_service.dto.CodeExecutionResponse;

public interface CodeExecutionService {
    CodeExecutionResponse executeCode(CodeExecutionRequest request);
}
