package com.hallak.shared_libraries.handlers;

import com.hallak.shared_libraries.errorhandling.APIError;
import com.hallak.shared_libraries.exceptions.PdfGenerationException;
import com.hallak.shared_libraries.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalRestControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new APIError(Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI()));
    }

        @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<APIError> dataIntegrityViolation(DataIntegrityViolationException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new APIError(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<APIError> usernameNotFound(UsernameNotFoundException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new APIError(Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI()));
    }

    @ExceptionHandler(PdfGenerationException.class)
    public ResponseEntity<APIError> pdfGeneration(PdfGenerationException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new APIError(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), request.getRequestURI()));
    }




}
