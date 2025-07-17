package com.coherentsolutions.ticketbookingsystem.mapper;

import com.coherentsolutions.ticketbookingsystem.dto.request.CreateUserRequest;
import com.coherentsolutions.ticketbookingsystem.dto.request.UpdateUserRequest;
import com.coherentsolutions.ticketbookingsystem.dto.response.UserResponse;
import com.coherentsolutions.ticketbookingsystem.entity.User;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * MapStruct mapper for User entity and DTOs.
 * 
 * This interface demonstrates:
 * - MapStruct mapping annotations
 * - Entity to DTO conversions
 * - DTO to entity conversions
 * - Custom mapping methods
 * - Null value handling
 * 
 * Compare with C# AutoMapper configurations.
 */
@Mapper(
    componentModel = "spring",          // Generate Spring component
    unmappedTargetPolicy = ReportingPolicy.IGNORE,  // Ignore unmapped fields
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE  // Ignore null values
)
public interface UserMapper {
    
    /**
     * Maps User entity to UserResponse DTO.
     * 
     * TODO: Configure date formatting
     * TODO: Add custom field mappings if needed
     * TODO: Handle null values appropriately
     */
    @Mapping(target = "createdAt", source = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "updatedAt", source = "updatedAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    // TODO: Add custom mapping for computed fields
    // TODO: Map full name if needed
    UserResponse toResponse(User user);
    
    /**
     * Maps CreateUserRequest DTO to User entity.
     * 
     * TODO: Ignore fields that should not be mapped
     * TODO: Add password hashing logic
     * TODO: Set default values for new entities
     */
    @Mapping(target = "id", ignore = true)                    // ID is generated
    @Mapping(target = "passwordHash", ignore = true)          // Will be set by service
    @Mapping(target = "createdAt", ignore = true)            // Will be set by service
    @Mapping(target = "updatedAt", ignore = true)            // Will be set by service
    @Mapping(target = "active", constant = "true")           // Default to active
    // TODO: Add custom mapping for password hashing
    User toEntity(CreateUserRequest request);
    
    /**
     * Maps UpdateUserRequest DTO to User entity for updates.
     * 
     * TODO: Handle partial updates (null values should be ignored)
     * TODO: Preserve existing values for unmapped fields
     * TODO: Update timestamp handling
     */
    @Mapping(target = "id", ignore = true)                    // ID should not be updated
    @Mapping(target = "passwordHash", ignore = true)          // Handle separately if provided
    @Mapping(target = "createdAt", ignore = true)            // Creation time should not change
    @Mapping(target = "updatedAt", ignore = true)            // Will be set by service
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UpdateUserRequest request, @MappingTarget User user);
    
    // TODO: Add custom mapping methods
    // TODO: Add conditional mapping methods
    // TODO: Add validation methods
    
    /**
     * Custom mapping method for date formatting.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add timezone support
     * - Implement locale-specific formatting
     * - Add multiple date format options
     */
    default String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
    
    /**
     * Custom mapping method for parsing date strings.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add multiple date format support
     * - Implement robust error handling
     * - Add timezone parsing
     */
    default LocalDateTime parseDateTime(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        
        try {
            return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            // Basic error handling - students can enhance
            return null;
        }
    }
    
    /**
     * Custom mapping method for full name.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add support for middle names
     * - Implement name formatting options
     * - Add internationalization support
     */
    default String mapFullName(String firstName, String lastName) {
        if (firstName == null && lastName == null) {
            return null;
        }
        
        StringBuilder result = new StringBuilder();
        if (firstName != null && !firstName.trim().isEmpty()) {
            result.append(firstName.trim());
        }
        if (lastName != null && !lastName.trim().isEmpty()) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(lastName.trim());
        }
        return result.toString();
    }
    
    // ENHANCEMENT OPPORTUNITIES for students:
    // - Add password hashing method
    // - Implement BCrypt password hashing
    // - Add password strength validation
    // - Add salt generation
    
    // ENHANCEMENT OPPORTUNITIES for students:
    // - Add email normalization method
    // - Add email format validation
    // - Implement domain normalization
    // - Add support for email aliases
    
    // ENHANCEMENT OPPORTUNITIES for students:
    // - Add phone number formatting method
    // - Add international phone number formatting
    // - Implement phone number validation
    // - Add region-specific formatting
    
    /**
     * Custom conditional mapping method.
     * TODO: Implement this method
     */
    @Condition
    default boolean isNotEmpty(String value) {
        // TODO: Check if string is not null and not empty
        // TODO: Handle whitespace-only strings
        
        return value != null && !value.trim().isEmpty();
    }
    
    /**
     * Custom mapping method for user status.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add more sophisticated status types
     * - Implement status internationalization
     * - Add status-specific styling information
     */
    default String mapUserStatus(Boolean active) {
        if (active == null) {
            return "Unknown";
        }
        
        return active ? "Active" : "Inactive";
    }
    
    // TODO: Add more custom mapping methods as needed
    // TODO: Add validation methods
    // TODO: Add transformation methods
    // TODO: Add conditional mapping methods
    
    /**
     * After mapping method for post-processing.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add audit field population
     * - Implement timezone handling
     * - Add validation after mapping
     */
    @AfterMapping
    default void setTimestamps(@MappingTarget User user) {
        LocalDateTime now = LocalDateTime.now();
        
        if (user.getId() == null) {
            user.setCreatedAt(now);
        }
        
        user.setUpdatedAt(now);
    }
    
    /**
     * Before mapping method for pre-processing.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add comprehensive request validation
     * - Implement business rule validation
     * - Add security checks
     */
    @BeforeMapping
    default void validateInput(CreateUserRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        
        // Basic validation - students can enhance
        if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
    }
}