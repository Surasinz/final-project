package com.example.final_project.exception;

import org.springframework.stereotype.Component;

@Component
public class exception {
    public class Exception extends RuntimeException {
    public Exception(String message) {
        super(message);
    }
}
}
