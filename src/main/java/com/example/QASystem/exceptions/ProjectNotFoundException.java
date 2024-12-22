package com.example.QASystem.exceptions;

public class ProjectNotFoundException extends RuntimeException {

    public ProjectNotFoundException(Integer id) {
        super("Проект с id='" + id + "' отсутствует");
    }
}
