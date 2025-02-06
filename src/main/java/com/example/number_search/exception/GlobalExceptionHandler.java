package com.example.number_search.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * Global exception handler for handling application-wide exceptions.
 * This class provides centralized exception handling for REST controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles NotEnoughNumbersException.
     * This exception is thrown when there are not enough numbers in the file
     * to determine the N-th maximum value.
     *
     * @param ex The thrown NotEnoughNumbersException.
     * @return ResponseEntity with HTTP status 400 (Bad Request) and the exception message.
     */
    @ExceptionHandler(NotEnoughNumbersException.class)
    public ResponseEntity<String> handleNotEnoughNumbersException(NotEnoughNumbersException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles FileParsingException.
     * This exception is thrown when an error occurs while reading the Excel file.
     *
     * @param ex The thrown FileParsingException.
     * @return ResponseEntity with HTTP status 400 (Bad Request) and the exception message.
     */
    @ExceptionHandler(FileParsingException.class)
    public ResponseEntity<String> handleFileParsingException(FileParsingException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Handles validation errors related to request parameters.
     * This occurs when constraints like @NotBlank or @Min fail.
     *
     * @param ex The thrown ConstraintViolationException.
     * @return ResponseEntity with HTTP status 400 (Bad Request) and validation error messages.
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    /**
     * Handles validation errors related to method arguments.
     * This occurs when @Valid fails inside a request body.
     *
     * @param ex The thrown MethodArgumentNotValidException.
     * @return ResponseEntity with HTTP status 400 (Bad Request) and validation error messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    /**
     * Handles missing request parameters.
     * This occurs when a required @RequestParam is missing from the request.
     *
     * @param ex The thrown MissingServletRequestParameterException.
     * @return ResponseEntity with HTTP status 400 (Bad Request) and an error message.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Missing required request parameter: " + ex.getParameterName());
    }

    /**
     * Handles all other unexpected exceptions.
     * This ensures that any unhandled exceptions are caught and a generic error message is returned.
     *
     * @param ex The thrown exception.
     * @return ResponseEntity with HTTP status 500 (Internal Server Error)
     * and a generic error message including the exception details.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An unexpected error occurred: " + ex.getMessage());
    }
}
