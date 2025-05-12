package com.example.QASystem.exceptions;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(Long id) {
        super("Проект с id='" + id + "' отсутствует");
    }
}
