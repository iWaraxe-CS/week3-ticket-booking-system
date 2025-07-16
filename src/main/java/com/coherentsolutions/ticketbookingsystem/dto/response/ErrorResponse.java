package com.coherentsolutions.ticketbookingsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Response DTO for error information.
 * 
 * This class demonstrates:
 * - Consistent error response format
 * - Structured error handling
 * - JSON serialization for errors
 * - Lombok usage for error DTOs
 * 
 * Compare with C# ProblemDetails and custom error responses.
 */
@Data                    // Generates getters, setters, toString, equals, hashCode
@Builder                 // Generates builder pattern
@NoArgsConstructor      // Default constructor (required by Jackson)
@AllArgsConstructor     // Constructor with all parameters
public class ErrorResponse {
    
    /**
     * Error code for programmatic handling.
     * TODO: Define standard error codes
     * TODO: Consider using enums for error codes
     */
    private String code;
    
    /**
     * Human-readable error message.
     * TODO: Implement internationalization
     * TODO: Consider different messages for different audiences
     */
    private String message;
    
    /**
     * Timestamp when the error occurred.
     * TODO: Ensure consistent timezone handling
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    
    /**
     * Additional error details (e.g., validation errors).
     * TODO: Structure validation errors consistently
     * TODO: Consider nested error objects
     */
    private Map<String, String> details;
    
    /**
     * HTTP status code associated with the error.
     * TODO: Ensure consistency with actual HTTP response status
     */
    private Integer status;
    
    /**
     * Request path where the error occurred.
     * TODO: Include this for debugging purposes
     */
    private String path;
    
    /**
     * Trace ID for request correlation.
     * TODO: Implement distributed tracing
     */
    private String traceId;
    
    // TODO: Add support for nested errors
    // TODO: Consider adding error severity levels
    // TODO: Add documentation links for error resolution
    
    /**
     * Helper method to add validation error.
     * TODO: Implement this method
     * 
     * @param field the field that failed validation
     * @param message the validation error message
     */
    public void addValidationError(String field, String message) {
        // TODO: Add field validation error to details map
        // Initialize details map if null
        // Handle multiple errors for the same field
    }
    
    /**
     * Helper method to check if error has validation details.
     * TODO: Implement this method
     * 
     * @return true if error has validation details
     */
    public boolean hasValidationErrors() {
        // TODO: Check if details map contains validation errors
        return false;
    }
    
    /**
     * Helper method to get formatted error message.
     * TODO: Implement this method
     * 
     * @return formatted error message with details
     */
    public String getFormattedMessage() {
        // TODO: Format message with details for logging
        // Include validation errors if present
        return null;
    }
    
    /**
     * Static factory method for creating validation error.
     * TODO: Implement this method
     * 
     * @param message the main error message
     * @param validationErrors map of field validation errors
     * @return ErrorResponse instance
     */
    public static ErrorResponse validationError(String message, Map<String, String> validationErrors) {
        // TODO: Create ErrorResponse with validation details
        // Set appropriate error code and status
        return null;
    }
    
    /**
     * Static factory method for creating resource not found error.
     * TODO: Implement this method
     * 
     * @param resourceType the type of resource not found
     * @param resourceId the ID of the resource not found
     * @return ErrorResponse instance
     */
    public static ErrorResponse notFound(String resourceType, String resourceId) {
        // TODO: Create ErrorResponse for resource not found
        // Set appropriate error code and message
        return null;
    }
    
    /**
     * Static factory method for creating internal server error.
     * TODO: Implement this method
     * 
     * @param message the error message
     * @return ErrorResponse instance
     */
    public static ErrorResponse internalError(String message) {
        // TODO: Create ErrorResponse for internal server error
        // Be careful not to expose sensitive information
        return null;
    }
}