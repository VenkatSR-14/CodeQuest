package com.example.interview_service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;
@Data
public class SolutionDto {

        @NotNull
        @Size(min = 1, max = 255, message = "Author name must be between 1 and 255 characters")
        private String author;

        @NotNull
        @Size(min = 1, message = "Content must not be empty")
        private String content;

        @NotNull
        private UUID problemId;

        public SolutionDto() {
        }

        public SolutionDto(UUID problemId, String content, String author){
            this.problemId = problemId;
            this.content = content;
            this.author = author;
        }

        // Getters
        public UUID getProblemId(){
            return this.problemId;
        }

        public String getAuthor(){
            return this.author;
        }

        public String getContent(){
            return this.content;
        }

        // Setters
        public void setContent(String content){
            this.content = content;
        }

        public void setAuthor(String author){
            this.author = author;
        }

        public void setProblemId(UUID id){
            this.problemId =id;
        }


}
