package com.campos.jpa_hib.resources.exceptions;

import com.campos.jpa_hib.services.exceptions.DatabaseException;
import com.campos.jpa_hib.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    // HANDLER 1: Quando ResourceNotFoundException é lançada
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(
            ResourceNotFoundException e,
            HttpServletRequest request) {

        // 1. Definir o tipo de erro
        String error = "Resource not found";

        // 2. Definir o status HTTP (404 = Not Found)
        HttpStatus status = HttpStatus.NOT_FOUND;

        // 3. Construir objeto StandardError com TODAS as informações
        StandardError err = new StandardError(
                Instant.now(),              // Quando aconteceu
                status.value(),             // 404
                error,                      // "Resource not found"
                e.getMessage(),             // "Resource not found. Id: 5"
                request.getRequestURI()     // "/users/5"
        );

        // 4. Retornar ResponseEntity com status 404 e corpo JSON
        return ResponseEntity.status(status).body(err);
    }

    // HANDLER 2: Quando DatabaseException é lançada
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(
            DatabaseException e,
            HttpServletRequest request) {

        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}