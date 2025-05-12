package com.example.QASystem.exceptions;

public class CheckListNotFoundException extends RuntimeException {

    public CheckListNotFoundException(Long id) {
        super("Чек-лист с id='" + id + "' отсутствует");
    }
}
