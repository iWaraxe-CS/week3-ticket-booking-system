package com.coherentsolutions.ticketbookingsystem.exception;

/**
 * Base exception for user service operations.
 * 
 * This exception demonstrates:
 * - Exception hierarchy design
 * - Service-specific exceptions
 * - Common exception behavior
 * - Error context preservation
 * 
 * Compare with C# custom exception hierarchies.
 */
public class UserServiceException extends RuntimeException {
    
    private final String operation;
    private final String userIdentifier;
    private final String errorCode;
    
    /**
     * Creates a new UserServiceException.
     * 
     * @param message the error message
     */
    public UserServiceException(String message) {
        super(message);
        this.operation = null;
        this.userIdentifier = null;
        this.errorCode = null;
    }
    
    /**
     * Creates a new UserServiceException with operation details.
     * 
     * @param message the error message
     * @param operation the operation that failed
     */
    public UserServiceException(String message, String operation) {
        super(message);
        this.operation = operation;
        this.userIdentifier = null;
        this.errorCode = null;
    }
    
    /**
     * Creates a new UserServiceException with full details.
     * 
     * @param message the error message
     * @param operation the operation that failed
     * @param userIdentifier the user identifier
     * @param errorCode the error code
     */
    public UserServiceException(String message, String operation, String userIdentifier, String errorCode) {
        super(message);
        this.operation = operation;
        this.userIdentifier = userIdentifier;
        this.errorCode = errorCode;
    }
    
    /**
     * Creates a new UserServiceException with cause.
     * 
     * @param message the error message
     * @param cause the underlying cause
     */
    public UserServiceException(String message, Throwable cause) {
        super(message, cause);
        this.operation = null;
        this.userIdentifier = null;
        this.errorCode = null;
    }
    
    /**
     * Creates a new UserServiceException with operation and cause.
     * 
     * @param message the error message
     * @param operation the operation that failed
     * @param cause the underlying cause
     */
    public UserServiceException(String message, String operation, Throwable cause) {
        super(message, cause);
        this.operation = operation;
        this.userIdentifier = null;
        this.errorCode = null;
    }
    
    // TODO: Add getters for operation details
    // TODO: Add factory methods for common scenarios
    // TODO: Add validation for parameters
    
    /**
     * Gets the operation that failed.
     * 
     * @return the operation name
     */
    public String getOperation() {
        return operation;
    }
    
    /**
     * Gets the user identifier.
     * 
     * @return the user identifier
     */
    public String getUserIdentifier() {
        return userIdentifier;
    }
    
    /**
     * Gets the error code.
     * 
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * Factory method for user creation failures.
     * TODO: Implement this method
     * 
     * @param cause the underlying cause
     * @return UserServiceException for creation failure
     */
    public static UserServiceException creationFailed(Throwable cause) {
        // TODO: Create creation-specific exception
        // return new UserServiceException("User creation failed", "CREATE_USER", cause);
        return null;
    }
    
    /**
     * Factory method for user update failures.
     * TODO: Implement this method
     * 
     * @param userId the user ID
     * @param cause the underlying cause
     * @return UserServiceException for update failure
     */
    public static UserServiceException updateFailed(String userId, Throwable cause) {
        // TODO: Create update-specific exception
        // return new UserServiceException("User update failed", "UPDATE_USER", userId, "UPDATE_FAILED", cause);
        return null;
    }
    
    /**
     * Factory method for user deletion failures.
     * TODO: Implement this method
     * 
     * @param userId the user ID
     * @param cause the underlying cause
     * @return UserServiceException for deletion failure
     */
    public static UserServiceException deletionFailed(String userId, Throwable cause) {
        // TODO: Create deletion-specific exception
        // return new UserServiceException("User deletion failed", "DELETE_USER", userId, "DELETE_FAILED", cause);
        return null;
    }
    
    /**
     * Factory method for user retrieval failures.
     * TODO: Implement this method
     * 
     * @param userId the user ID
     * @param cause the underlying cause
     * @return UserServiceException for retrieval failure
     */
    public static UserServiceException retrievalFailed(String userId, Throwable cause) {
        // TODO: Create retrieval-specific exception
        // return new UserServiceException("User retrieval failed", "GET_USER", userId, "RETRIEVAL_FAILED", cause);
        return null;
    }
    
    /**
     * Gets a formatted error message including operation details.
     * TODO: Implement this method
     * 
     * @return formatted error message
     */
    public String getFormattedMessage() {
        // TODO: Format message with operation details
        // TODO: Include user identifier if available
        // TODO: Include error code if available
        
        StringBuilder sb = new StringBuilder(getMessage());
        
        if (operation != null) {
            sb.append(" (Operation: ").append(operation).append(")");
        }
        
        if (userIdentifier != null) {
            sb.append(" (User: ").append(userIdentifier).append(")");
        }
        
        if (errorCode != null) {
            sb.append(" (Code: ").append(errorCode).append(")");
        }
        
        return sb.toString();
    }
    
    /**
     * Checks if this exception is related to a specific operation.
     * TODO: Implement this method
     * 
     * @param operationName the operation name to check
     * @return true if related to the operation
     */
    public boolean isRelatedToOperation(String operationName) {
        // TODO: Check if exception is related to specific operation
        // TODO: Handle case sensitivity
        
        return operation != null && operation.equalsIgnoreCase(operationName);
    }
}