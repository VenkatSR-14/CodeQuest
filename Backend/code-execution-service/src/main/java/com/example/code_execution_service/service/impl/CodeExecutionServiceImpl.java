package com.example.code_execution_service.service.impl;

import com.example.code_execution_service.dto.CodeExecutionRequest;
import com.example.code_execution_service.dto.CodeExecutionResponse;
import com.example.code_execution_service.service.CodeExecutionService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CodeExecutionServiceImpl implements CodeExecutionService {

    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private static final int EXECUTION_TIMEOUT_SECONDS = 5;
    private static final long MEMORY_LIMIT_BYTES = 100 * 1024 * 1024; // 100 MB

    @Override
    public CodeExecutionResponse executeCode(CodeExecutionRequest request) {
        String language = request.getLanguage().toLowerCase();
        String code = request.getCode();
        
        // Generate a unique ID for this execution
        String executionId = UUID.randomUUID().toString();
        
        try {
            switch (language) {
                case "python":
                    return executePythonCode(code, executionId, request);
                case "javascript":
                    return executeJavaScriptCode(code, executionId, request);
                case "java":
                    return executeJavaCode(code, executionId, request);
                case "cpp":
                    return executeCppCode(code, executionId, request);
                default:
                    return new CodeExecutionResponse(false, "", 
                            "Unsupported language: " + language, 0, null);
            }
        } catch (Exception e) {
            return new CodeExecutionResponse(false, "", 
                    "Execution failed: " + e.getMessage(), 0, null);
        }
    }

    private CodeExecutionResponse executePythonCode(String code, String executionId, 
            CodeExecutionRequest request) throws IOException {
        // Create temporary directory for this execution
        Path tempDir = createTempDir(executionId);
        Path codeFile = tempDir.resolve("code.py");
        
        // Write code to file
        Files.write(codeFile, code.getBytes(StandardCharsets.UTF_8));
        
        // Execute code in sandbox
        long startTime = System.currentTimeMillis();
        ProcessBuilder pb = new ProcessBuilder("python", codeFile.toString());
        pb.directory(tempDir.toFile());
        
        // Set environment variables for sandboxing
        Map<String, String> env = pb.environment();
        env.put("PYTHONPATH", tempDir.toString());
        
        // Execute and capture output
        Process process = pb.start();
        
        try {
            // Set timeout for execution
            boolean completed = process.waitFor(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            if (!completed) {
                process.destroyForcibly();
                return new CodeExecutionResponse(false, "", 
                        "Execution timed out after " + EXECUTION_TIMEOUT_SECONDS + " seconds", 
                        System.currentTimeMillis() - startTime, null);
            }
            
            // Read output and error streams
            String output = readProcessOutput(process);
            String error = readProcessError(process);
            
            // Check exit code
            int exitCode = process.exitValue();
            boolean success = exitCode == 0;
            
            return new CodeExecutionResponse(success, output, error, 
                    System.currentTimeMillis() - startTime, null);
        } catch (InterruptedException e) {
            process.destroyForcibly();
            return new CodeExecutionResponse(false, "", 
                    "Execution interrupted: " + e.getMessage(), 
                    System.currentTimeMillis() - startTime, null);
        } finally {
            // Clean up temporary files
            deleteDirectory(tempDir.toFile());
        }
    }

    private CodeExecutionResponse executeJavaScriptCode(String code, String executionId, 
            CodeExecutionRequest request) throws IOException {
        // Create temporary directory for this execution
        Path tempDir = createTempDir(executionId);
        Path codeFile = tempDir.resolve("code.js");
        
        // Write code to file
        Files.write(codeFile, code.getBytes(StandardCharsets.UTF_8));
        
        // Execute code in sandbox using Node.js
        long startTime = System.currentTimeMillis();
        ProcessBuilder pb = new ProcessBuilder("node", codeFile.toString());
        pb.directory(tempDir.toFile());
        
        // Execute and capture output
        Process process = pb.start();
        
        try {
            // Set timeout for execution
            boolean completed = process.waitFor(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            if (!completed) {
                process.destroyForcibly();
                return new CodeExecutionResponse(false, "", 
                        "Execution timed out after " + EXECUTION_TIMEOUT_SECONDS + " seconds", 
                        System.currentTimeMillis() - startTime, null);
            }
            
            // Read output and error streams
            String output = readProcessOutput(process);
            String error = readProcessError(process);
            
            // Check exit code
            int exitCode = process.exitValue();
            boolean success = exitCode == 0;
            
            return new CodeExecutionResponse(success, output, error, 
                    System.currentTimeMillis() - startTime, null);
        } catch (InterruptedException e) {
            process.destroyForcibly();
            return new CodeExecutionResponse(false, "", 
                    "Execution interrupted: " + e.getMessage(), 
                    System.currentTimeMillis() - startTime, null);
        } finally {
            // Clean up temporary files
            deleteDirectory(tempDir.toFile());
        }
    }

    private CodeExecutionResponse executeJavaCode(String code, String executionId, 
            CodeExecutionRequest request) throws IOException {
        // Create temporary directory for this execution
        Path tempDir = createTempDir(executionId);
        
        // Determine class name from code or use default
        String className = extractClassName(code);
        if (className == null) {
            className = "Solution";
            // Wrap code in a class if not already
            code = "public class Solution {\n" + code + "\n}";
        }
        
        Path codeFile = tempDir.resolve(className + ".java");
        
        // Write code to file
        Files.write(codeFile, code.getBytes(StandardCharsets.UTF_8));
        
        // Compile Java code
        long startTime = System.currentTimeMillis();
        ProcessBuilder compilePb = new ProcessBuilder("javac", codeFile.toString());
        compilePb.directory(tempDir.toFile());
        
        Process compileProcess = compilePb.start();
        
        try {
            boolean compileCompleted = compileProcess.waitFor(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            if (!compileCompleted) {
                compileProcess.destroyForcibly();
                return new CodeExecutionResponse(false, "", 
                        "Compilation timed out after " + EXECUTION_TIMEOUT_SECONDS + " seconds", 
                        System.currentTimeMillis() - startTime, null);
            }
            
            String compileError = readProcessError(compileProcess);
            if (!compileError.isEmpty()) {
                return new CodeExecutionResponse(false, "", 
                        "Compilation error: " + compileError, 
                        System.currentTimeMillis() - startTime, null);
            }
            
            // Execute compiled Java code
            ProcessBuilder runPb = new ProcessBuilder("java", "-Xmx100m", className);
            runPb.directory(tempDir.toFile());
            
            Process runProcess = runPb.start();
            
            boolean runCompleted = runProcess.waitFor(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            if (!runCompleted) {
                runProcess.destroyForcibly();
                return new CodeExecutionResponse(false, "", 
                        "Execution timed out after " + EXECUTION_TIMEOUT_SECONDS + " seconds", 
                        System.currentTimeMillis() - startTime, null);
            }
            
            // Read output and error streams
            String output = readProcessOutput(runProcess);
            String error = readProcessError(runProcess);
            
            // Check exit code
            int exitCode = runProcess.exitValue();
            boolean success = exitCode == 0;
            
            return new CodeExecutionResponse(success, output, error, 
                    System.currentTimeMillis() - startTime, null);
        } catch (InterruptedException e) {
            return new CodeExecutionResponse(false, "", 
                    "Execution interrupted: " + e.getMessage(), 
                    System.currentTimeMillis() - startTime, null);
        } finally {
            // Clean up temporary files
            deleteDirectory(tempDir.toFile());
        }
    }

    private CodeExecutionResponse executeCppCode(String code, String executionId, 
            CodeExecutionRequest request) throws IOException {
        // Create temporary directory for this execution
        Path tempDir = createTempDir(executionId);
        Path codeFile = tempDir.resolve("code.cpp");
        Path execFile = tempDir.resolve("code.exe");
        
        // Write code to file
        Files.write(codeFile, code.getBytes(StandardCharsets.UTF_8));
        
        // Compile C++ code
        long startTime = System.currentTimeMillis();
        ProcessBuilder compilePb = new ProcessBuilder("g++", codeFile.toString(), "-o", execFile.toString());
        compilePb.directory(tempDir.toFile());
        
        Process compileProcess = compilePb.start();
        
        try {
            boolean compileCompleted = compileProcess.waitFor(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            if (!compileCompleted) {
                compileProcess.destroyForcibly();
                return new CodeExecutionResponse(false, "", 
                        "Compilation timed out after " + EXECUTION_TIMEOUT_SECONDS + " seconds", 
                        System.currentTimeMillis() - startTime, null);
            }
            
            String compileError = readProcessError(compileProcess);
            if (!compileError.isEmpty()) {
                return new CodeExecutionResponse(false, "", 
                        "Compilation error: " + compileError, 
                        System.currentTimeMillis() - startTime, null);
            }
            
            // Execute compiled C++ code
            ProcessBuilder runPb = new ProcessBuilder(execFile.toString());
            runPb.directory(tempDir.toFile());
            
            Process runProcess = runPb.start();
            
            boolean runCompleted = runProcess.waitFor(EXECUTION_TIMEOUT_SECONDS, TimeUnit.SECONDS);
            if (!runCompleted) {
                runProcess.destroyForcibly();
                return new CodeExecutionResponse(false, "", 
                        "Execution timed out after " + EXECUTION_TIMEOUT_SECONDS + " seconds", 
                        System.currentTimeMillis() - startTime, null);
            }
            
            // Read output and error streams
            String output = readProcessOutput(runProcess);
            String error = readProcessError(runProcess);
            
            // Check exit code
            int exitCode = runProcess.exitValue();
            boolean success = exitCode == 0;
            
            return new CodeExecutionResponse(success, output, error, 
                    System.currentTimeMillis() - startTime, null);
        } catch (InterruptedException e) {
            return new CodeExecutionResponse(false, "", 
                    "Execution interrupted: " + e.getMessage(), 
                    System.currentTimeMillis() - startTime, null);
        } finally {
            // Clean up temporary files
            deleteDirectory(tempDir.toFile());
        }
    }

    private Path createTempDir(String executionId) throws IOException {
        Path tempDir = Paths.get(TEMP_DIR, "codequest-execution-" + executionId);
        Files.createDirectories(tempDir);
        return tempDir;
    }

    private void deleteDirectory(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
        directory.delete();
    }

    private String readProcessOutput(Process process) throws IOException {
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }
        }
        return output.toString();
    }

    private String readProcessError(Process process) throws IOException {
        StringBuilder error = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getErrorStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                error.append(line).append("\n");
            }
        }
        return error.toString();
    }

    private String extractClassName(String code) {
        // Simple regex to extract class name from Java code
        // This is a basic implementation and might need improvements
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
                "public\\s+class\\s+(\\w+)\\s*\\{");
        java.util.regex.Matcher matcher = pattern.matcher(code);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
