package com.coherentsolutions.ticketbookingsystem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Response DTO for user information.
 * 
 * This class demonstrates:
 * - Response DTO pattern for API output
 * - Proper data exposure (no sensitive fields)
 * - JSON serialization configuration
 * - Lombok usage for DTOs
 * 
 * Compare with C# DTOs and record types.
 */
@Data                    // Generates getters, setters, toString, equals, hashCode
@Builder                 // Generates builder pattern
@NoArgsConstructor      // Default constructor (required by Jackson)
@AllArgsConstructor     // Constructor with all parameters
public class UserResponse {
    
    /**
     * User's unique identifier.
     * TODO: Consider using UUID for better security
     */
    private Long id;
    
    /**
     * User's email address.
     * TODO: Consider masking email for privacy
     */
    private String email;
    
    /**
     * User's first name.
     * TODO: Add null handling
     */
    private String firstName;
    
    /**
     * User's last name.
     * TODO: Add null handling
     */
    private String lastName;
    
    /**
     * User's phone number.
     * TODO: Consider masking phone number for privacy
     */
    private String phoneNumber;
    
    /**
     * User's account status.
     * TODO: Consider using enum for status
     */
    private Boolean active;
    
    /**
     * Timestamp when the user was created.
     * TODO: Format consistently across all timestamps
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    
    /**
     * Timestamp when the user was last updated.
     * TODO: Format consistently across all timestamps
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    
    // Note: passwordHash is NOT included in response for security
    // TODO: Add additional computed fields
    // TODO: Consider adding HATEOAS links for REST maturity
    
    /**
     * Computed field for full name.
     * TODO: Implement this method
     * 
     * @return formatted full name
     */
    public String getFullName() {
        // TODO: Implement full name concatenation
        // Handle null values appropriately
        return null;
    }
    
    /**
     * Helper method to get formatted creation date.
     * TODO: Implement this method
     * 
     * @return formatted creation date string
     */
    public String getFormattedCreatedAt() {
        // TODO: Format date for display
        // Consider locale-specific formatting
        return null;
    }
    
    /**
     * Helper method to get user status as string.
     * TODO: Implement this method
     * 
     * @return user status as readable string
     */
    public String getStatusText() {
        // TODO: Convert boolean active to readable status
        // e.g., "Active", "Inactive"
        return null;
    }
    
    /**
     * Helper method to check if user data is complete.
     * TODO: Implement this method
     * 
     * @return true if all required fields are populated
     */
    public boolean isComplete() {
        // TODO: Check if all required fields are not null/empty
        // Useful for UI to determine if profile is complete
        return false;
    }
    
    /**
     * Helper method to get days since creation.
     * TODO: Implement this method
     * 
     * @return number of days since user was created
     */
    public long getDaysSinceCreation() {
        // TODO: Calculate days between createdAt and now
        // Useful for user engagement metrics
        return 0;
    }
}