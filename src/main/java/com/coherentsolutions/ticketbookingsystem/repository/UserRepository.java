package com.coherentsolutions.ticketbookingsystem.repository;

import com.coherentsolutions.ticketbookingsystem.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for user data access operations.
 * 
 * This interface demonstrates:
 * - Repository pattern abstraction
 * - Data access layer separation
 * - Optional return types for nullable results
 * - Standard CRUD operations
 * 
 * Compare with C# repository pattern and Entity Framework.
 */
public interface UserRepository {
    
    /**
     * Saves a user entity to the storage.
     * 
     * TODO: Handle ID generation for new entities
     * TODO: Update existing entities properly
     * TODO: Ensure thread safety
     * 
     * @param user the user entity to save
     * @return the saved user entity with generated ID
     */
    User save(User user);
    
    /**
     * Finds a user by their ID.
     * 
     * TODO: Implement efficient lookup
     * TODO: Handle null/invalid IDs
     * 
     * @param id the user ID
     * @return Optional containing user if found, empty otherwise
     */
    Optional<User> findById(Long id);
    
    /**
     * Finds a user by their email address.
     * 
     * TODO: Implement email-based lookup
     * TODO: Handle case sensitivity
     * TODO: Ensure email uniqueness
     * 
     * @param email the user email
     * @return Optional containing user if found, empty otherwise
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Retrieves all users from storage.
     * 
     * TODO: Consider memory implications for large datasets
     * TODO: Implement proper ordering
     * 
     * @return list of all users
     */
    List<User> findAll();
    
    /**
     * Retrieves users with pagination.
     * 
     * TODO: Implement pagination logic
     * TODO: Handle edge cases (page out of bounds)
     * TODO: Ensure consistent ordering
     * 
     * @param offset the number of records to skip
     * @param limit the maximum number of records to return
     * @return list of users for the requested page
     */
    List<User> findAll(int offset, int limit);
    
    /**
     * Deletes a user by their ID.
     * 
     * TODO: Implement deletion
     * TODO: Handle non-existent users
     * TODO: Consider soft delete vs hard delete
     * 
     * @param id the user ID to delete
     * @return true if user was deleted, false if not found
     */
    boolean deleteById(Long id);
    
    /**
     * Deletes a user entity.
     * 
     * TODO: Implement entity-based deletion
     * TODO: Handle null entities
     * 
     * @param user the user entity to delete
     * @return true if user was deleted, false if not found
     */
    boolean delete(User user);
    
    /**
     * Checks if a user exists by ID.
     * 
     * TODO: Implement efficient existence check
     * TODO: Avoid loading full entity
     * 
     * @param id the user ID
     * @return true if user exists, false otherwise
     */
    boolean existsById(Long id);
    
    /**
     * Checks if a user exists by email.
     * 
     * TODO: Implement email-based existence check
     * TODO: Handle case sensitivity
     * 
     * @param email the user email
     * @return true if user exists, false otherwise
     */
    boolean existsByEmail(String email);
    
    /**
     * Counts total number of users.
     * 
     * TODO: Implement efficient counting
     * TODO: Consider filtering active users only
     * 
     * @return total number of users
     */
    long count();
    
    /**
     * Finds users by name (first name or last name).
     * 
     * TODO: Implement name-based search
     * TODO: Handle partial matches
     * TODO: Consider case-insensitive search
     * 
     * @param searchTerm the search term
     * @return list of matching users
     */
    List<User> findByNameContaining(String searchTerm);
    
    /**
     * Finds active users only.
     * 
     * TODO: Implement active user filtering
     * TODO: Consider pagination for large datasets
     * 
     * @return list of active users
     */
    List<User> findByActiveTrue();
    
    /**
     * Finds inactive users only.
     * 
     * TODO: Implement inactive user filtering
     * TODO: Consider pagination for large datasets
     * 
     * @return list of inactive users
     */
    List<User> findByActiveFalse();
    
    /**
     * Finds users created after a specific date.
     * 
     * TODO: Implement date-based filtering
     * TODO: Handle timezone considerations
     * 
     * @param date the date to filter by
     * @return list of users created after the date
     */
    List<User> findByCreatedAtAfter(java.time.LocalDateTime date);
    
    /**
     * Deletes all users from storage.
     * 
     * TODO: Implement bulk deletion
     * TODO: Consider confirmation mechanisms
     * TODO: Handle transaction rollback
     * 
     * WARNING: This method should be used with caution!
     */
    void deleteAll();
}