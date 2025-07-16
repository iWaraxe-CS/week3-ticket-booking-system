package com.coherentsolutions.ticketbookingsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for creating a new user.
 * 
 * This class demonstrates:
 * - Jakarta Bean Validation annotations (compare with C# Data Annotations)
 * - Request DTO pattern for API input
 * - Lombok for reducing boilerplate code
 * - Proper validation constraints
 */
@Data                    // Generates getters, setters, toString, equals, hashCode
@Builder                 // Generates builder pattern
@NoArgsConstructor      // Default constructor (required by Jackson)
@AllArgsConstructor     // Constructor with all parameters
public class CreateUserRequest {
    
    /**
     * User's email address - must be unique and valid format.
     * Compare with C#: [Required, EmailAddress]
     */
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    @Size(max = 100, message = "Email must be less than 100 characters")
    private String email;
    
    /**
     * User's password - must meet strength requirements.
     * TODO: Implement custom password validation
     * TODO: Consider password complexity rules
     */
    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 50, message = "Password must be between 8 and 50 characters")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character"
    )
    private String password;
    
    /**
     * User's first name.
     * TODO: Add additional validation rules
     * TODO: Consider name format validation
     */
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z\\s'-]+$", message = "First name can only contain letters, spaces, hyphens, and apostrophes")
    private String firstName;
    
    /**
     * User's last name.
     * TODO: Add additional validation rules
     * TODO: Consider name format validation
     */
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z\\s'-]+$", message = "Last name can only contain letters, spaces, hyphens, and apostrophes")
    private String lastName;
    
    /**
     * User's phone number.
     * TODO: Implement phone number validation
     * TODO: Consider international phone number support
     */
    @Pattern(
        regexp = "^\\+?[1-9]\\d{1,14}$",
        message = "Phone number must be valid international format"
    )
    private String phoneNumber;
    
    // TODO: Add custom validation annotations
    // TODO: Consider cross-field validation (e.g., password confirmation)
    // TODO: Add validation groups for different scenarios
    
    /**
     * Custom validation method example.
     * TODO: Implement custom validation logic
     * 
     * @return true if the request is valid
     */
    public boolean isValid() {
        // TODO: Implement custom validation logic
        // This could include business rules not covered by annotations
        return true;
    }
    
    /**
     * Helper method to get full name.
     * TODO: Implement this method
     * 
     * @return formatted full name
     */
    public String getFullName() {
        // TODO: Implement full name concatenation
        // Handle null values appropriately
        return null;
    }
}