package com.coherentsolutions.ticketbookingsystem.repository.impl;

import com.coherentsolutions.ticketbookingsystem.entity.User;
import com.coherentsolutions.ticketbookingsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * In-memory implementation of UserRepository.
 * 
 * This class demonstrates:
 * - Repository pattern implementation
 * - Thread-safe in-memory storage
 * - Concurrent collections usage
 * - Stream API operations
 * 
 * Note: This implementation is for development/testing only.
 * In production, use JPA repositories with database persistence.
 */
@Repository                     // Marks this as a Spring repository component
@Slf4j                         // Generates logger field
public class InMemoryUserRepository implements UserRepository {
    
    // Thread-safe storage for users
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    
    // Atomic counter for generating unique IDs
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    /**
     * Saves a user entity to the in-memory storage.
     * 
     * Basic implementation that handles:
     * - ID generation for new users
     * - Updates existing users 
     * - Sets timestamps appropriately
     * - Thread-safe storage
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add comprehensive validation
     * - Implement optimistic locking
     * - Add audit logging
     * - Performance monitoring
     */
    @Override
    public User save(User user) {
        log.debug("Saving user: {}", user != null ? user.getEmail() : "null");
        
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        
        LocalDateTime now = LocalDateTime.now();
        
        // Handle new user creation
        if (user.getId() == null) {
            Long newId = generateNextId();
            user.setId(newId);
            user.setCreatedAt(now);
            user.setUpdatedAt(now);
            log.debug("Creating new user with ID: {}", newId);
        } else {
            // Handle existing user update
            user.setUpdatedAt(now);
            log.debug("Updating existing user with ID: {}", user.getId());
        }
        
        // Store in concurrent map (thread-safe)
        users.put(user.getId(), copyUser(user));
        
        log.debug("User saved successfully with ID: {}", user.getId());
        return copyUser(user);
    }
    
    /**
     * Finds a user by their ID.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add caching for frequently accessed users
     * - Implement query optimization
     * - Add metrics collection
     */
    @Override
    public Optional<User> findById(Long id) {
        log.debug("Finding user by ID: {}", id);
        
        if (id == null) {
            return Optional.empty();
        }
        
        User user = users.get(id);
        return user != null ? Optional.of(copyUser(user)) : Optional.empty();
    }
    
    /**
     * Finds a user by their email address.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add email indexing for O(1) lookup
     * - Implement more sophisticated email validation
     * - Add support for email aliases
     */
    @Override
    public Optional<User> findByEmail(String email) {
        log.debug("Finding user by email: {}", email);
        
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        
        String normalizedEmail = normalizeEmail(email);
        
        return users.values().stream()
                .filter(user -> normalizedEmail.equals(normalizeEmail(user.getEmail())))
                .findFirst()
                .map(this::copyUser);
    }
    
    /**
     * Retrieves all users from storage.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add sorting options (by name, email, creation date)
     * - Implement result caching
     * - Add filtering capabilities
     */
    @Override
    public List<User> findAll() {
        log.debug("Finding all users. Total count: {}", users.size());
        
        return users.values().stream()
                .map(this::copyUser)
                .sorted((u1, u2) -> u1.getId().compareTo(u2.getId()))
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves users with pagination.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add parameter validation with meaningful error messages
     * - Implement cursor-based pagination for better performance
     * - Add total count information
     */
    @Override
    public List<User> findAll(int offset, int limit) {
        log.debug("Finding users with pagination - offset: {}, limit: {}", offset, limit);
        
        if (offset < 0) offset = 0;
        if (limit <= 0) limit = 10;
        
        return users.values().stream()
                .map(this::copyUser)
                .sorted((u1, u2) -> u1.getId().compareTo(u2.getId()))
                .skip(offset)
                .limit(limit)
                .collect(Collectors.toList());
    }
    
    /**
     * Deletes a user by their ID.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Implement soft delete instead of hard delete
     * - Add cascade deletion for related entities
     * - Add audit logging for deleted users
     */
    @Override
    public boolean deleteById(Long id) {
        log.debug("Deleting user by ID: {}", id);
        
        if (id == null) {
            return false;
        }
        
        User removedUser = users.remove(id);
        boolean wasRemoved = removedUser != null;
        
        if (wasRemoved) {
            log.debug("User with ID {} deleted successfully", id);
        } else {
            log.debug("User with ID {} not found for deletion", id);
        }
        
        return wasRemoved;
    }
    
    /**
     * Deletes a user entity.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add entity validation
     * - Implement optimistic locking checks
     */
    @Override
    public boolean delete(User user) {
        log.debug("Deleting user: {}", user != null ? user.getEmail() : "null");
        
        if (user == null || user.getId() == null) {
            return false;
        }
        
        return deleteById(user.getId());
    }
    
    /**
     * Checks if a user exists by ID.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add caching for existence checks
     * - Optimize for frequent lookups
     */
    @Override
    public boolean existsById(Long id) {
        log.debug("Checking if user exists by ID: {}", id);
        
        if (id == null) {
            return false;
        }
        
        return users.containsKey(id);
    }
    
    /**
     * Checks if a user exists by email.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add email indexing for O(1) lookup
     * - Implement case-insensitive search optimization
     */
    @Override
    public boolean existsByEmail(String email) {
        log.debug("Checking if user exists by email: {}", email);
        
        return findByEmail(email).isPresent();
    }
    
    /**
     * Counts total number of users.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add filtering options (active users only, by role, etc.)
     * - Implement caching for count operations
     */
    @Override
    public long count() {
        log.debug("Counting users");
        
        return users.size();
    }
    
    /**
     * Finds users by name (first name or last name).
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add fuzzy search capabilities
     * - Implement full-text search
     * - Add search result ranking
     */
    @Override
    public List<User> findByNameContaining(String searchTerm) {
        log.debug("Searching users by name: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String normalizedSearchTerm = searchTerm.toLowerCase().trim();
        
        return users.values().stream()
                .filter(user -> {
                    String firstName = user.getFirstName() != null ? user.getFirstName().toLowerCase() : "";
                    String lastName = user.getLastName() != null ? user.getLastName().toLowerCase() : "";
                    return firstName.contains(normalizedSearchTerm) || lastName.contains(normalizedSearchTerm);
                })
                .map(this::copyUser)
                .collect(Collectors.toList());
    }
    
    /**
     * Finds active users only.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add indexing for better performance
     * - Implement caching for active users
     */
    @Override
    public List<User> findByActiveTrue() {
        log.debug("Finding active users");
        
        return users.values().stream()
                .filter(User::isActive)
                .map(this::copyUser)
                .collect(Collectors.toList());
    }
    
    /**
     * Finds inactive users only.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add indexing for better performance
     * - Implement automatic cleanup of inactive users
     */
    @Override
    public List<User> findByActiveFalse() {
        log.debug("Finding inactive users");
        
        return users.values().stream()
                .filter(user -> !user.isActive())
                .map(this::copyUser)
                .collect(Collectors.toList());
    }
    
    /**
     * Finds users created after a specific date.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add date range queries
     * - Implement timezone handling
     * - Add date indexing for performance
     */
    @Override
    public List<User> findByCreatedAtAfter(LocalDateTime date) {
        log.debug("Finding users created after: {}", date);
        
        if (date == null) {
            return new ArrayList<>();
        }
        
        return users.values().stream()
                .filter(user -> user.getCreatedAt() != null && user.getCreatedAt().isAfter(date))
                .map(this::copyUser)
                .collect(Collectors.toList());
    }
    
    /**
     * Deletes all users from storage.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add confirmation mechanisms
     * - Implement backup before deletion
     * - Add audit logging
     */
    @Override
    public void deleteAll() {
        log.warn("Deleting all users from storage");
        
        int deletedCount = users.size();
        users.clear();
        idGenerator.set(1);
        
        log.warn("Deleted {} users from storage", deletedCount);
    }
    
    // TODO: Add helper methods for common operations
    // TODO: Add data validation methods
    // TODO: Add bulk operations
    
    /**
     * Helper method to generate next ID.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Implement UUID generation for better scalability
     * - Add ID recycling for deleted users
     */
    private Long generateNextId() {
        return idGenerator.getAndIncrement();
    }
    
    /**
     * Helper method to normalize email.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add email validation
     * - Implement domain normalization
     * - Add support for international email formats
     */
    private String normalizeEmail(String email) {
        if (email == null) {
            return null;
        }
        return email.trim().toLowerCase();
    }
    
    /**
     * Helper method to validate user before saving.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add comprehensive field validation
     * - Implement business rule validation
     * - Add custom validation annotations
     */
    private void validateUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        
        // Basic validation - students can enhance this
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty");
        }
        
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty");
        }
    }
    
    /**
     * Helper method to create a copy of user for thread safety.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Implement deep cloning for complex objects
     * - Add copy validation
     * - Optimize copying for performance
     */
    private User copyUser(User user) {
        if (user == null) {
            return null;
        }
        
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .passwordHash(user.getPasswordHash())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .active(user.isActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
    
    /**
     * Demo method to populate with sample data.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add configuration-based sample data loading
     * - Implement data seeding from external files
     * - Add environment-specific sample data
     */
    public void initializeWithSampleData() {
        log.info("Initializing repository with sample data");
        
        // Sample users for testing
        User admin = User.builder()
                .email("admin@example.com")
                .passwordHash("hashed-password-admin")
                .firstName("Admin")
                .lastName("User")
                .phoneNumber("+1234567890")
                .active(true)
                .build();
        
        User testUser = User.builder()
                .email("test@example.com")
                .passwordHash("hashed-password-test")
                .firstName("Test")
                .lastName("User")
                .phoneNumber("+0987654321")
                .active(true)
                .build();
        
        save(admin);
        save(testUser);
        
        log.info("Sample data initialized with {} users", users.size());
    }
}