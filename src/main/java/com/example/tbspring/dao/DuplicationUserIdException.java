package com.example.tbspring.dao;

public class DuplicationUserIdException extends RuntimeException {
    public DuplicationUserIdException(Throwable cause) {
        super(cause);
    }
}
