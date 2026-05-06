package com.prakash.gateaway_service.Exception;

public record ExceptionResponse(int status,
                                String message,
                                long timestamp,
                                String path) {
    public ExceptionResponse(int status, String message, String path) {
        this(status, message, System.currentTimeMillis(), path);
    }
}
