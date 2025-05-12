package com.example.QASystem.exceptions;

public class TestCaseNotFoundException extends RuntimeException {

    public TestCaseNotFoundException(Long id) {
        super("Тест-кейс с id='" + id + "' отсутствует");
    }
}
