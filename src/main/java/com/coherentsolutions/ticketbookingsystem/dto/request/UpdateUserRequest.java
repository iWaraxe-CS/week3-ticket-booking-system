package com.coherentsolutions.ticketbookingsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for updating an existing user.
 * 
 * This class demonstrates:
 * - Partial update pattern (fields can be null)
 * - Different validation rules for updates vs creation
 * - Optional field handling
 * - Lombok usage for DTOs
 */
@Data                    // Generates getters, setters, toString, equals, hashCode
@Builder                 // Generates builder pattern
@NoArgsConstructor      // Default constructor (required by Jackson)
@AllArgsConstructor     // Constructor with all parameters
public class UpdateUserRequest {
    
    /**
     * User's email address - optional for updates.
     * TODO: Add email uniqueness validation
     * TODO: Consider email change verification process
     */
    @Email(message = "Email must be valid format")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;
    
    /**
     * User's new password - optional for updates.
     * TODO: Implement password change validation
     * TODO: Consider requiring current password for security
     */
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character"
    )
    private String password;
    
    /**
     * User's first name - optional for updates.
     * TODO: Add validation for non-empty if provided
     */
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z\\s'-]+$", message = "First name can only contain letters, spaces, hyphens, and apostrophes")
    private String firstName;
    
    /**
     * User's last name - optional for updates.
     * TODO: Add validation for non-empty if provided
     */
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z\\s'-]+$", message = "Last name can only contain letters, spaces, hyphens, and apostrophes")
    private String lastName;
    
    /**
     * User's phone number - optional for updates.
     * TODO: Implement phone number validation
     * TODO: Consider phone number change verification
     */
    @Pattern(
        regexp = "^\\+?[1-9]\\d{1,14}$",
        message = "Phone number must be valid international format"
    )
    private String phoneNumber;
    
    /**
     * Flag to indicate if user should be activated/deactivated.
     * TODO: Implement user status management
     * TODO: Consider role-based access for this field
     */
    private Boolean active;
    
    // TODO: Add validation groups for different update scenarios
    // TODO: Consider audit fields (who made the change, when)
    // TODO: Add custom validation for business rules
    
    /**
     * Helper method to check if any field is being updated.
     * TODO: Implement this method
     * 
     * @return true if at least one field is not null
     */
    public boolean hasUpdates() {
        // TODO: Check if any field is not null
        // This helps determine if the update request is meaningful
        return false;
    }
    
    /**
     * Helper method to get list of fields being updated.
     * TODO: Implement this method
     * 
     * @return list of field names that are being updated
     */
    public java.util.List<String> getUpdatedFields() {
        // TODO: Return list of non-null field names
        // Useful for logging and audit purposes
        return java.util.Collections.emptyList();
    }
    
    /**
     * Custom validation for update-specific business rules.
     * TODO: Implement this method
     * 
     * @return true if update is valid
     */
    public boolean isValidUpdate() {
        // TODO: Implement business rule validation
        // e.g., can't deactivate your own account
        return true;
    }
}