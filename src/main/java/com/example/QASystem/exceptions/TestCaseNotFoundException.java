package com.example.QASystem.exceptions;

public class TestCaseNotFoundException extends RuntimeException {

    public TestCaseNotFoundException(Integer id) {
        super("Тест-кейс с id='" + id + "' отсутствует");
    }
}
