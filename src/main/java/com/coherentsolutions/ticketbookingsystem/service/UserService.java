package com.coherentsolutions.ticketbookingsystem.service;

import com.coherentsolutions.ticketbookingsystem.dto.request.CreateUserRequest;
import com.coherentsolutions.ticketbookingsystem.dto.request.UpdateUserRequest;
import com.coherentsolutions.ticketbookingsystem.dto.response.UserResponse;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for user operations.
 * 
 * This interface demonstrates:
 * - Service layer abstraction
 * - Business logic separation
 * - DTO usage instead of entities
 * - Optional return types for nullable results
 * 
 * Compare with C# service interfaces and dependency injection.
 */
public interface UserService {
    
    /**
     * Creates a new user in the system.
     * 
     * TODO: Implement validation logic
     * TODO: Check for duplicate email addresses
     * TODO: Hash password before storage
     * TODO: Set creation timestamp
     * 
     * @param request the user creation request
     * @return the created user response
     * @throws IllegalArgumentException if request is invalid
     * @throws RuntimeException if email already exists
     */
    UserResponse createUser(CreateUserRequest request);
    
    /**
     * Retrieves a user by their ID.
     * 
     * TODO: Implement user lookup logic
     * TODO: Handle non-existent users
     * TODO: Consider security filtering
     * 
     * @param id the user ID
     * @return Optional containing user if found, empty otherwise
     */
    Optional<UserResponse> findById(Long id);
    
    /**
     * Retrieves a user by their email address.
     * 
     * TODO: Implement email-based lookup
     * TODO: Handle case sensitivity
     * TODO: Consider email normalization
     * 
     * @param email the user email
     * @return Optional containing user if found, empty otherwise
     */
    Optional<UserResponse> findByEmail(String email);
    
    /**
     * Retrieves all users in the system.
     * 
     * TODO: Implement pagination
     * TODO: Consider filtering and sorting
     * TODO: Handle large datasets efficiently
     * 
     * @return list of all users
     */
    List<UserResponse> findAll();
    
    /**
     * Retrieves all users with pagination.
     * 
     * TODO: Implement pagination logic
     * TODO: Validate page parameters
     * TODO: Return pagination metadata
     * 
     * @param page the page number (0-based)
     * @param size the page size
     * @return list of users for the requested page
     */
    List<UserResponse> findAll(int page, int size);
    
    /**
     * Updates an existing user.
     * 
     * TODO: Implement partial update logic
     * TODO: Validate user existence
     * TODO: Check for email uniqueness on update
     * TODO: Update modification timestamp
     * 
     * @param id the user ID to update
     * @param request the update request
     * @return the updated user response
     * @throws RuntimeException if user not found
     */
    UserResponse updateUser(Long id, UpdateUserRequest request);
    
    /**
     * Deletes a user by ID (soft delete recommended).
     * 
     * TODO: Implement soft delete logic
     * TODO: Validate user existence
     * TODO: Handle cascade deletion if needed
     * TODO: Consider audit logging
     * 
     * @param id the user ID to delete
     * @throws RuntimeException if user not found
     */
    void deleteUser(Long id);
    
    /**
     * Activates a user account.
     * 
     * TODO: Implement activation logic
     * TODO: Validate user existence
     * TODO: Handle already active users
     * 
     * @param id the user ID to activate
     * @throws RuntimeException if user not found
     */
    void activateUser(Long id);
    
    /**
     * Deactivates a user account.
     * 
     * TODO: Implement deactivation logic
     * TODO: Validate user existence
     * TODO: Handle already inactive users
     * 
     * @param id the user ID to deactivate
     * @throws RuntimeException if user not found
     */
    void deactivateUser(Long id);
    
    /**
     * Checks if a user exists by ID.
     * 
     * TODO: Implement existence check
     * TODO: Consider caching for performance
     * 
     * @param id the user ID
     * @return true if user exists, false otherwise
     */
    boolean existsById(Long id);
    
    /**
     * Checks if a user exists by email.
     * 
     * TODO: Implement email existence check
     * TODO: Handle case sensitivity
     * 
     * @param email the user email
     * @return true if user exists, false otherwise
     */
    boolean existsByEmail(String email);
    
    /**
     * Counts total number of users.
     * 
     * TODO: Implement count logic
     * TODO: Consider filtering active users only
     * 
     * @return total number of users
     */
    long countUsers();
    
    /**
     * Searches users by name.
     * 
     * TODO: Implement search logic
     * TODO: Handle partial matches
     * TODO: Consider case-insensitive search
     * 
     * @param searchTerm the search term
     * @return list of matching users
     */
    List<UserResponse> searchByName(String searchTerm);
}