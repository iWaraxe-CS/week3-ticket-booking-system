package com.coherentsolutions.ticketbookingsystem.controller;

import com.coherentsolutions.ticketbookingsystem.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for consistent error responses.
 * 
 * This class demonstrates:
 * - Global exception handling with @RestControllerAdvice
 * - Consistent error response format
 * - Validation error handling
 * - HTTP status code mapping
 * - Request context access
 * 
 * Compare with C# ASP.NET Core exception filters and middleware.
 */
@RestControllerAdvice          // Global exception handler for REST controllers
@Slf4j                         // Logging support
public class GlobalExceptionHandler {
    
    /**
     * Handles validation errors from @Valid annotations.
     * 
     * TODO: Implement validation error handling
     * TODO: Extract field errors properly
     * TODO: Return 400 Bad Request with error details
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(
            MethodArgumentNotValidException ex, 
            WebRequest request) {
        
        log.warn("Validation error occurred: {}", ex.getMessage());
        
        // Extract field errors from exception
        Map<String, String> fieldErrors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        }
        
        // Build ErrorResponse with validation details
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("VALIDATION_ERROR")
                .message("Validation failed for request")
                .status(HttpStatus.BAD_REQUEST.value())
                .path(getRequestPath(request))
                .details(fieldErrors)
                .timestamp(LocalDateTime.now())
                .build();
        
        // Return 400 Bad Request
        return ResponseEntity.badRequest().body(errorResponse);
        
        // ENHANCEMENT OPPORTUNITIES for students:
        // - Add field-specific validation messages
        // - Implement localized validation messages
        // - Add validation group support
        // - Implement custom validation error formatting
    }
    
    // ENHANCEMENT OPPORTUNITIES for students:
    // - Add specific ResourceNotFoundException handler
    // - Implement custom exception types
    // - Add resource-specific error messages
    
    /**
     * Handles illegal argument exceptions.
     * 
     * TODO: Implement illegal argument handling
     * TODO: Return 400 Bad Request with error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, 
            WebRequest request) {
        
        log.warn("Illegal argument: {}", ex.getMessage());
        
        // Build ErrorResponse with 400 status
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("BAD_REQUEST")
                .message(sanitizeErrorMessage(ex.getMessage()))
                .status(HttpStatus.BAD_REQUEST.value())
                .path(getRequestPath(request))
                .timestamp(LocalDateTime.now())
                .build();
        
        // Return 400 Bad Request
        return ResponseEntity.badRequest().body(errorResponse);
        
        // ENHANCEMENT OPPORTUNITIES for students:
        // - Add specific error codes for different argument types
        // - Implement detailed error context
        // - Add suggestion for fixing the error
        // - Implement error categorization
    }
    
    // ENHANCEMENT OPPORTUNITIES for students:
    // - Add specific DuplicateResourceException handler
    // - Implement conflict resolution suggestions
    // - Add duplicate resource detection
    
    // ENHANCEMENT OPPORTUNITIES for students:
    // - Add specific RuntimeException handler if needed
    // - Implement exception categorization
    // - Add runtime exception metrics
    
    /**
     * Handles all other exceptions.
     * 
     * TODO: Implement catch-all exception handling
     * TODO: Return 500 Internal Server Error
     * TODO: Log full exception details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, 
            WebRequest request) {
        
        log.error("Unexpected exception occurred", ex);
        
        // Build ErrorResponse with 500 status
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code("INTERNAL_SERVER_ERROR")
                .message("An unexpected error occurred. Please try again later.")
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .path(getRequestPath(request))
                .traceId(generateTraceId())
                .timestamp(LocalDateTime.now())
                .build();
        
        // Return 500 Internal Server Error
        return ResponseEntity.internalServerError().body(errorResponse);
        
        // ENHANCEMENT OPPORTUNITIES for students:
        // - Add detailed error logging
        // - Implement error notification system
        // - Add error metrics collection
        // - Implement error recovery suggestions
    }
    
    // TODO: Add custom exception handlers for business logic exceptions
    // TODO: Add security exception handlers
    // TODO: Add database exception handlers
    // TODO: Add external service exception handlers
    
    /**
     * Helper method to build ErrorResponse.
     * TODO: Implement this method
     */
    private ErrorResponse buildErrorResponse(
            String code, 
            String message, 
            HttpStatus status, 
            String path) {
        
        // Build ErrorResponse with all fields
        return ErrorResponse.builder()
                .code(code)
                .message(message)
                .status(status.value())
                .path(path)
                .timestamp(LocalDateTime.now())
                .traceId(generateTraceId())
                .build();
        
        // ENHANCEMENT OPPORTUNITIES for students:
        // - Add request correlation ID
        // - Include user context if available
        // - Add error severity levels
        // - Implement error documentation links
    }
    
    /**
     * Helper method to extract request path.
     * TODO: Implement this method
     */
    private String getRequestPath(WebRequest request) {
        // Extract path from WebRequest
        if (request == null) {
            return "unknown";
        }
        
        // Get the request URI
        String path = request.getDescription(false);
        if (path != null && path.startsWith("uri=")) {
            path = path.substring(4); // Remove "uri=" prefix
        }
        
        return path != null ? path : "unknown";
        
        // ENHANCEMENT OPPORTUNITIES for students:
        // - Extract HTTP method information
        // - Add query parameters to path
        // - Implement request ID tracking
        // - Add client IP information
    }
    
    /**
     * Helper method to generate trace ID.
     * TODO: Implement this method
     */
    private String generateTraceId() {
        // Generate unique trace ID using UUID
        return "trace-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        
        // ENHANCEMENT OPPORTUNITIES for students:
        // - Implement distributed tracing correlation
        // - Add request context to trace ID
        // - Use custom trace ID format
        // - Integrate with APM tools
    }
    
    // ENHANCEMENT OPPORTUNITIES for students:
    // - Add helper methods for exception type detection
    // - Implement custom exception hierarchy
    // - Add exception classification logic
    
    /**
     * Helper method to sanitize error message for public consumption.
     */
    private String sanitizeErrorMessage(String message) {
        // Handle null messages
        if (message == null) {
            return "An error occurred";
        }
        
        // Basic sanitization - remove sensitive patterns
        String sanitized = message
                .replaceAll("password", "[REDACTED]")
                .replaceAll("token", "[REDACTED]")
                .replaceAll("key", "[REDACTED]");
        
        // Provide user-friendly message
        return sanitized;
        
        // ENHANCEMENT OPPORTUNITIES for students:
        // - Add comprehensive sensitive data patterns
        // - Implement message localization
        // - Add context-aware error messages
        // - Implement error message templates
    }
}