package com.coherentsolutions.ticketbookingsystem.exception;

/**
 * Exception thrown when attempting to create a resource that already exists.
 * 
 * This exception demonstrates:
 * - Conflict handling in REST APIs
 * - Resource uniqueness validation
 * - Meaningful error messages for duplicates
 * - HTTP 409 Conflict scenarios
 * 
 * Compare with C# custom exceptions and validation.
 */
public class DuplicateResourceException extends RuntimeException {
    
    private final String resourceType;
    private final String conflictField;
    private final String conflictValue;
    
    /**
     * Creates a new DuplicateResourceException.
     * 
     * @param message the error message
     */
    public DuplicateResourceException(String message) {
        super(message);
        this.resourceType = null;
        this.conflictField = null;
        this.conflictValue = null;
    }
    
    /**
     * Creates a new DuplicateResourceException with resource details.
     * 
     * @param resourceType the type of resource that conflicts
     * @param conflictField the field that has the conflict
     * @param conflictValue the value that conflicts
     */
    public DuplicateResourceException(String resourceType, String conflictField, String conflictValue) {
        super(String.format("%s with %s '%s' already exists", resourceType, conflictField, conflictValue));
        this.resourceType = resourceType;
        this.conflictField = conflictField;
        this.conflictValue = conflictValue;
    }
    
    /**
     * Creates a new DuplicateResourceException with custom message and details.
     * 
     * @param message the error message
     * @param resourceType the type of resource that conflicts
     * @param conflictField the field that has the conflict
     * @param conflictValue the value that conflicts
     */
    public DuplicateResourceException(String message, String resourceType, String conflictField, String conflictValue) {
        super(message);
        this.resourceType = resourceType;
        this.conflictField = conflictField;
        this.conflictValue = conflictValue;
    }
    
    /**
     * Creates a new DuplicateResourceException with cause.
     * 
     * @param message the error message
     * @param cause the underlying cause
     */
    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
        this.resourceType = null;
        this.conflictField = null;
        this.conflictValue = null;
    }
    
    // TODO: Add getters for conflict details
    // TODO: Add factory methods for common scenarios
    // TODO: Add validation for parameters
    
    /**
     * Gets the type of resource that has the conflict.
     * 
     * @return the resource type
     */
    public String getResourceType() {
        return resourceType;
    }
    
    /**
     * Gets the field that has the conflict.
     * 
     * @return the conflict field
     */
    public String getConflictField() {
        return conflictField;
    }
    
    /**
     * Gets the value that conflicts.
     * 
     * @return the conflict value
     */
    public String getConflictValue() {
        return conflictValue;
    }
    
    /**
     * Factory method for duplicate email exceptions.
     * TODO: Implement this method
     * 
     * @param email the duplicate email
     * @return DuplicateResourceException for email
     */
    public static DuplicateResourceException duplicateEmail(String email) {
        // TODO: Create email-specific exception
        // return new DuplicateResourceException("User", "email", email);
        return null;
    }
    
    /**
     * Factory method for duplicate phone number exceptions.
     * TODO: Implement this method
     * 
     * @param phoneNumber the duplicate phone number
     * @return DuplicateResourceException for phone number
     */
    public static DuplicateResourceException duplicatePhoneNumber(String phoneNumber) {
        // TODO: Create phone number-specific exception
        // return new DuplicateResourceException("User", "phone number", phoneNumber);
        return null;
    }
    
    /**
     * Factory method for duplicate user exceptions.
     * TODO: Implement this method
     * 
     * @param field the field that is duplicate
     * @param value the value that is duplicate
     * @return DuplicateResourceException for user
     */
    public static DuplicateResourceException duplicateUser(String field, String value) {
        // TODO: Create user-specific exception
        // return new DuplicateResourceException("User", field, value);
        return null;
    }
}