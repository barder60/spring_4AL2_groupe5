package com.gotta_watch_them_all.app.infrastructure.entrypoint.exception;

public class NotFoundException extends Exception {
    public NotFoundException(String message) {
        super(message);
    }
}
