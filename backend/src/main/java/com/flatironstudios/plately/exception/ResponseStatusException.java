package com.flatironstudios.plately.exception;

import org.springframework.http.HttpStatus;

public class ResponseStatusException extends RuntimeException {
    public HttpStatus status;

    public ResponseStatusException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
