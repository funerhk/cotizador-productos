package com.cotizador.utils;


import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponse extends Response {
    public ApiResponse(String message, Integer code, HttpStatus status, LocalDateTime timestamp) {
        super(message, code, status, timestamp);
    }
}
