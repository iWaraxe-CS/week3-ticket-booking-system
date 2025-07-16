package com.coherentsolutions.ticketbookingsystem.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception thrown when business validation fails.
 * 
 * This exception demonstrates:
 * - Business rule validation
 * - Field-level error collection
 * - Validation error details
 * - HTTP 400 Bad Request scenarios
 * 
 * Compare with C# validation exceptions and ModelState.
 */
public class ValidationException extends RuntimeException {
    
    private final Map<String, String> validationErrors;
    private final String validationContext;
    
    /**
     * Creates a new ValidationException.
     * 
     * @param message the error message
     */
    public ValidationException(String message) {
        super(message);
        this.validationErrors = new HashMap<>();
        this.validationContext = null;
    }
    
    /**
     * Creates a new ValidationException with validation errors.
     * 
     * @param message the error message
     * @param validationErrors map of field errors
     */
    public ValidationException(String message, Map<String, String> validationErrors) {
        super(message);
        this.validationErrors = validationErrors != null ? new HashMap<>(validationErrors) : new HashMap<>();
        this.validationContext = null;
    }
    
    /**
     * Creates a new ValidationException with context.
     * 
     * @param message the error message
     * @param validationContext the context where validation failed
     */
    public ValidationException(String message, String validationContext) {
        super(message);
        this.validationErrors = new HashMap<>();
        this.validationContext = validationContext;
    }
    
    /**
     * Creates a new ValidationException with errors and context.
     * 
     * @param message the error message
     * @param validationErrors map of field errors
     * @param validationContext the context where validation failed
     */
    public ValidationException(String message, Map<String, String> validationErrors, String validationContext) {
        super(message);
        this.validationErrors = validationErrors != null ? new HashMap<>(validationErrors) : new HashMap<>();
        this.validationContext = validationContext;
    }
    
    /**
     * Creates a new ValidationException with cause.
     * 
     * @param message the error message
     * @param cause the underlying cause
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
        this.validationErrors = new HashMap<>();
        this.validationContext = null;
    }
    
    // TODO: Add methods to manage validation errors
    // TODO: Add factory methods for common scenarios
    // TODO: Add validation for parameters
    
    /**
     * Gets the validation errors.
     * 
     * @return map of field errors
     */
    public Map<String, String> getValidationErrors() {
        return new HashMap<>(validationErrors);
    }
    
    /**
     * Gets the validation context.
     * 
     * @return the validation context
     */
    public String getValidationContext() {
        return validationContext;
    }
    
    /**
     * Adds a field validation error.
     * TODO: Implement this method
     * 
     * @param field the field name
     * @param error the error message
     */
    public void addFieldError(String field, String error) {
        // TODO: Add field error to validation errors map
        // TODO: Handle null/empty field names
        // TODO: Handle null/empty error messages
        
        if (field != null && !field.trim().isEmpty() && error != null && !error.trim().isEmpty()) {
            validationErrors.put(field, error);
        }
    }
    
    /**
     * Checks if there are validation errors.
     * TODO: Implement this method
     * 
     * @return true if there are validation errors
     */
    public boolean hasValidationErrors() {
        // TODO: Check if validation errors map is not empty
        return !validationErrors.isEmpty();
    }
    
    /**
     * Gets error message for a specific field.
     * TODO: Implement this method
     * 
     * @param field the field name
     * @return error message for the field
     */
    public String getFieldError(String field) {
        // TODO: Get error message for specific field
        // TODO: Handle null/empty field names
        
        if (field == null || field.trim().isEmpty()) {
            return null;
        }
        
        return validationErrors.get(field);
    }
    
    /**
     * Factory method for single field validation error.
     * TODO: Implement this method
     * 
     * @param field the field name
     * @param error the error message
     * @return ValidationException for field
     */
    public static ValidationException fieldError(String field, String error) {
        // TODO: Create validation exception with single field error
        // ValidationException ex = new ValidationException("Validation failed");
        // ex.addFieldError(field, error);
        // return ex;
        return null;
    }
    
    /**
     * Factory method for email validation error.
     * TODO: Implement this method
     * 
     * @param email the invalid email
     * @return ValidationException for email
     */
    public static ValidationException invalidEmail(String email) {
        // TODO: Create email-specific validation exception
        // return fieldError("email", "Invalid email format: " + email);
        return null;
    }
    
    /**
     * Factory method for password validation error.
     * TODO: Implement this method
     * 
     * @param reason the reason password is invalid
     * @return ValidationException for password
     */
    public static ValidationException invalidPassword(String reason) {
        // TODO: Create password-specific validation exception
        // return fieldError("password", "Invalid password: " + reason);
        return null;
    }
    
    /**
     * Factory method for phone number validation error.
     * TODO: Implement this method
     * 
     * @param phoneNumber the invalid phone number
     * @return ValidationException for phone number
     */
    public static ValidationException invalidPhoneNumber(String phoneNumber) {
        // TODO: Create phone number-specific validation exception
        // return fieldError("phoneNumber", "Invalid phone number format: " + phoneNumber);
        return null;
    }
    
    /**
     * Factory method for required field validation error.
     * TODO: Implement this method
     * 
     * @param field the required field name
     * @return ValidationException for required field
     */
    public static ValidationException requiredField(String field) {
        // TODO: Create required field validation exception
        // return fieldError(field, field + " is required");
        return null;
    }
    
    /**
     * Factory method for business rule validation error.
     * TODO: Implement this method
     * 
     * @param rule the business rule that failed
     * @return ValidationException for business rule
     */
    public static ValidationException businessRule(String rule) {
        // TODO: Create business rule validation exception
        // return new ValidationException("Business rule validation failed: " + rule);
        return null;
    }
    
    /**
     * Gets a formatted error message including all field errors.
     * TODO: Implement this method
     * 
     * @return formatted error message
     */
    public String getFormattedMessage() {
        // TODO: Format message with all validation errors
        // TODO: Include field errors in readable format
        
        StringBuilder sb = new StringBuilder(getMessage());
        
        if (hasValidationErrors()) {
            sb.append(" Field errors: ");
            validationErrors.forEach((field, error) -> 
                sb.append(String.format("%s: %s; ", field, error))
            );
        }
        
        return sb.toString();
    }
}