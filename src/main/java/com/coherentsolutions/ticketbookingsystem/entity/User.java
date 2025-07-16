package com.coherentsolutions.ticketbookingsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * User entity representing a user in the ticket booking system.
 * 
 * This entity demonstrates:
 * - Lombok annotations for reducing boilerplate code
 * - Builder pattern for object creation
 * - Proper field naming conventions
 * 
 * Compare with C# POCO classes and record types.
 */
@Data                    // Generates getters, setters, toString, equals, hashCode
@Builder                 // Generates builder pattern (like C# record with init)
@NoArgsConstructor      // Generates default constructor
@AllArgsConstructor     // Generates constructor with all parameters
public class User {
    
    /**
     * Unique identifier for the user.
     * TODO: Add validation annotations if needed
     */
    private Long id;
    
    /**
     * User's email address - must be unique across the system.
     * TODO: Add email validation annotation
     * TODO: Consider adding unique constraint for database persistence
     */
    private String email;
    
    /**
     * Hashed password for authentication.
     * TODO: Implement password hashing logic
     * TODO: Never store plain text passwords
     * Note: This field should never be exposed in DTOs
     */
    private String passwordHash;
    
    /**
     * User's first name.
     * TODO: Add validation for required field
     * TODO: Consider length constraints
     */
    private String firstName;
    
    /**
     * User's last name.
     * TODO: Add validation for required field
     * TODO: Consider length constraints
     */
    private String lastName;
    
    /**
     * User's phone number.
     * TODO: Add phone number format validation
     * TODO: Consider international phone number support
     */
    private String phoneNumber;
    
    /**
     * Timestamp when the user was created.
     * TODO: Set automatically on creation
     * TODO: Consider timezone handling
     */
    private LocalDateTime createdAt;
    
    /**
     * Timestamp when the user was last updated.
     * TODO: Update automatically on modification
     * TODO: Consider timezone handling
     */
    private LocalDateTime updatedAt;
    
    /**
     * Flag indicating whether the user account is active.
     * TODO: Implement soft delete functionality
     * TODO: Consider using enum for user status (ACTIVE, INACTIVE, SUSPENDED)
     */
    private boolean active = true;
    
    // TODO: Add audit fields (createdBy, updatedBy) for production systems
    // TODO: Consider adding roles/permissions for future security implementation
    // TODO: Add validation constraints using Bean Validation annotations
    
    /**
     * Helper method to get full name.
     * TODO: Implement this method
     * 
     * @return formatted full name
     */
    public String getFullName() {
        // TODO: Implement full name concatenation
        // Consider handling null values appropriately
        return null;
    }
    
    /**
     * Helper method to check if user is active.
     * TODO: Implement this method
     * 
     * @return true if user is active, false otherwise
     */
    public boolean isActive() {
        // TODO: Implement active status check
        return active;
    }
    
    /**
     * Helper method for soft delete.
     * TODO: Implement this method
     * 
     * Sets the user as inactive instead of deleting from database
     */
    public void deactivate() {
        // TODO: Implement soft delete logic
        // Set active to false and update timestamp
    }
    
    /**
     * Helper method to reactivate user.
     * TODO: Implement this method
     */
    public void activate() {
        // TODO: Implement activation logic
        // Set active to true and update timestamp
    }
}