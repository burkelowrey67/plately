package com.flatironstudios.plately.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;

public class ApiError {
    private int status;
    private String message;
    private Instant timestamp;

    public ApiError(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
        this.timestamp = Instant.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}