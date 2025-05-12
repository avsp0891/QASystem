package com.example.QASystem;

import com.example.QASystem.exceptions.CheckListNotFoundException;
import com.example.QASystem.exceptions.ProjectNotFoundException;
import com.example.QASystem.exceptions.TestCaseNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<String> handleProjectNotFoundException(ProjectNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(TestCaseNotFoundException.class)
    public ResponseEntity<String> handleTestCaseNotFoundException(TestCaseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CheckListNotFoundException.class)
    public ResponseEntity<String> handleCheckListNotFoundException(CheckListNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }


}
