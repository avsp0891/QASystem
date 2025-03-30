package com.example.QASystem.exceptions;

public class CheckListNotFoundException extends RuntimeException {

    public CheckListNotFoundException(Integer id) {
        super("Чек-лист с id='" + id + "' отсутствует");
    }
}
