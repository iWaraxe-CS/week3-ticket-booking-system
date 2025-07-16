package com.coherentsolutions.ticketbookingsystem.exception;

/**
 * Exception thrown when a requested resource is not found.
 * 
 * This exception demonstrates:
 * - Custom exception creation
 * - Proper exception hierarchy
 * - Meaningful error messages
 * - Resource identification
 * 
 * Compare with C# custom exceptions and exception handling.
 */
public class ResourceNotFoundException extends RuntimeException {
    
    private final String resourceType;
    private final String resourceId;
    
    /**
     * Creates a new ResourceNotFoundException.
     * 
     * @param message the error message
     */
    public ResourceNotFoundException(String message) {
        super(message);
        this.resourceType = null;
        this.resourceId = null;
    }
    
    /**
     * Creates a new ResourceNotFoundException with resource details.
     * 
     * @param resourceType the type of resource not found
     * @param resourceId the ID of the resource not found
     */
    public ResourceNotFoundException(String resourceType, String resourceId) {
        super(String.format("%s with ID '%s' not found", resourceType, resourceId));
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }
    
    /**
     * Creates a new ResourceNotFoundException with custom message and resource details.
     * 
     * @param message the error message
     * @param resourceType the type of resource not found
     * @param resourceId the ID of the resource not found
     */
    public ResourceNotFoundException(String message, String resourceType, String resourceId) {
        super(message);
        this.resourceType = resourceType;
        this.resourceId = resourceId;
    }
    
    /**
     * Creates a new ResourceNotFoundException with cause.
     * 
     * @param message the error message
     * @param cause the underlying cause
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
        this.resourceType = null;
        this.resourceId = null;
    }
    
    // TODO: Add getters for resource type and ID
    // TODO: Add factory methods for common scenarios
    // TODO: Add validation for parameters
    
    /**
     * Gets the type of resource that was not found.
     * 
     * @return the resource type
     */
    public String getResourceType() {
        return resourceType;
    }
    
    /**
     * Gets the ID of the resource that was not found.
     * 
     * @return the resource ID
     */
    public String getResourceId() {
        return resourceId;
    }
    
    /**
     * Factory method for user not found exceptions.
     * TODO: Implement this method
     * 
     * @param userId the user ID
     * @return ResourceNotFoundException for user
     */
    public static ResourceNotFoundException userNotFound(Long userId) {
        // TODO: Create user-specific exception
        // return new ResourceNotFoundException("User", userId.toString());
        return null;
    }
    
    /**
     * Factory method for user not found by email exceptions.
     * TODO: Implement this method
     * 
     * @param email the user email
     * @return ResourceNotFoundException for user
     */
    public static ResourceNotFoundException userNotFoundByEmail(String email) {
        // TODO: Create user-specific exception for email lookup
        // return new ResourceNotFoundException("User", email);
        return null;
    }
}