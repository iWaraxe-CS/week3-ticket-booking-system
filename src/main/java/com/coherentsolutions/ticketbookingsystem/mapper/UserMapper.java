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
     * TODO: Implement this method
     */
    default String formatDateTime(LocalDateTime dateTime) {
        // TODO: Format LocalDateTime to string
        // TODO: Handle null values
        // TODO: Use consistent date format
        
        if (dateTime == null) {
            return null;
        }
        
        // TODO: Use DateTimeFormatter for consistent formatting
        // return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        return null; // Placeholder
    }
    
    /**
     * Custom mapping method for parsing date strings.
     * TODO: Implement this method
     */
    default LocalDateTime parseDateTime(String dateString) {
        // TODO: Parse string to LocalDateTime
        // TODO: Handle null/empty values
        // TODO: Handle parsing errors
        
        if (dateString == null || dateString.trim().isEmpty()) {
            return null;
        }
        
        // TODO: Use DateTimeFormatter for parsing
        // return LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        return null; // Placeholder
    }
    
    /**
     * Custom mapping method for full name.
     * TODO: Implement this method
     */
    default String mapFullName(String firstName, String lastName) {
        // TODO: Combine first and last name
        // TODO: Handle null values
        // TODO: Handle empty strings
        
        if (firstName == null && lastName == null) {
            return null;
        }
        
        // TODO: Implement full name logic
        // StringBuilder result = new StringBuilder();
        // if (firstName != null && !firstName.trim().isEmpty()) {
        //     result.append(firstName.trim());
        // }
        // if (lastName != null && !lastName.trim().isEmpty()) {
        //     if (result.length() > 0) {
        //         result.append(" ");
        //     }
        //     result.append(lastName.trim());
        // }
        // return result.toString();
        
        return null; // Placeholder
    }
    
    /**
     * Custom mapping method for password hashing.
     * TODO: Implement this method
     */
    default String hashPassword(String plainPassword) {
        // TODO: Hash password using BCrypt or similar
        // TODO: Handle null passwords
        // TODO: Use proper password hashing library
        
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            return null;
        }
        
        // TODO: Use BCryptPasswordEncoder or similar
        // return passwordEncoder.encode(plainPassword);
        
        return null; // Placeholder - students should implement
    }
    
    /**
     * Custom mapping method for email normalization.
     * TODO: Implement this method
     */
    default String normalizeEmail(String email) {
        // TODO: Normalize email (lowercase, trim)
        // TODO: Handle null values
        // TODO: Validate email format
        
        if (email == null) {
            return null;
        }
        
        // TODO: Implement email normalization
        // return email.trim().toLowerCase();
        
        return null; // Placeholder
    }
    
    /**
     * Custom mapping method for phone number formatting.
     * TODO: Implement this method
     */
    default String formatPhoneNumber(String phoneNumber) {
        // TODO: Format phone number consistently
        // TODO: Handle null values
        // TODO: Remove non-numeric characters
        
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return null;
        }
        
        // TODO: Implement phone number formatting
        // String cleaned = phoneNumber.replaceAll("[^\\d+]", "");
        // return cleaned;
        
        return null; // Placeholder
    }
    
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
     * TODO: Implement this method
     */
    default String mapUserStatus(Boolean active) {
        // TODO: Convert boolean to readable status
        // TODO: Handle null values
        
        if (active == null) {
            return "Unknown";
        }
        
        // TODO: Return appropriate status text
        // return active ? "Active" : "Inactive";
        
        return null; // Placeholder
    }
    
    // TODO: Add more custom mapping methods as needed
    // TODO: Add validation methods
    // TODO: Add transformation methods
    // TODO: Add conditional mapping methods
    
    /**
     * After mapping method for post-processing.
     * TODO: Implement this method
     */
    @AfterMapping
    default void setTimestamps(@MappingTarget User user) {
        // TODO: Set created/updated timestamps
        // TODO: Handle new vs existing entities
        
        LocalDateTime now = LocalDateTime.now();
        
        if (user.getId() == null) {
            // TODO: Set creation timestamp for new entities
            // user.setCreatedAt(now);
        }
        
        // TODO: Always set updated timestamp
        // user.setUpdatedAt(now);
    }
    
    /**
     * Before mapping method for pre-processing.
     * TODO: Implement this method
     */
    @BeforeMapping
    default void validateInput(CreateUserRequest request) {
        // TODO: Add custom validation logic
        // TODO: Throw exceptions for invalid input
        
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        
        // TODO: Add more validation logic
    }
}