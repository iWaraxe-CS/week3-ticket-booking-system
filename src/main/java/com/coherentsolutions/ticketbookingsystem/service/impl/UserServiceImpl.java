package com.coherentsolutions.ticketbookingsystem.service.impl;

import com.coherentsolutions.ticketbookingsystem.dto.request.CreateUserRequest;
import com.coherentsolutions.ticketbookingsystem.dto.request.UpdateUserRequest;
import com.coherentsolutions.ticketbookingsystem.dto.response.UserResponse;
import com.coherentsolutions.ticketbookingsystem.entity.User;
import com.coherentsolutions.ticketbookingsystem.mapper.UserMapper;
import com.coherentsolutions.ticketbookingsystem.repository.UserRepository;
import com.coherentsolutions.ticketbookingsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Implementation of UserService interface.
 * 
 * This class demonstrates:
 * - Service layer implementation
 * - Constructor injection with Lombok
 * - Logging with SLF4J
 * - Business logic organization
 * 
 * Compare with C# service implementations and dependency injection.
 */
@Service                        // Marks this as a Spring service component
@RequiredArgsConstructor       // Generates constructor for final fields (DI)
@Slf4j                         // Generates logger field (compare with ILogger<T>)
public class UserServiceImpl implements UserService {
    
    // Injected dependencies - basic implementation
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    // TODO: Inject PasswordEncoder dependency (for future security implementation)
    // ENHANCEMENT OPPORTUNITIES for students:
    // - Add password encoding service
    // - Implement email service for notifications
    // - Add caching service
    // - Add audit logging service
    
    /**
     * Creates a new user in the system.
     * 
     * Basic implementation that demonstrates:
     * - Request validation
     * - Email uniqueness check
     * - Entity creation and persistence
     * - Response mapping
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add proper password hashing (BCrypt)
     * - Implement email verification
     * - Add user role assignment
     * - Add audit logging
     * - Implement transaction management
     */
    @Override
    public UserResponse createUser(CreateUserRequest request) {
        log.info("Creating user with email: {}", request.getEmail());
        
        // Basic validation
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        
        // Check for duplicate email
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("User with email " + request.getEmail() + " already exists");
        }
        
        // Convert request to entity
        User user = userMapper.toEntity(request);
        
        // Save user (repository will handle ID generation and timestamps)
        User savedUser = userRepository.save(user);
        
        // Convert to response DTO
        UserResponse response = userMapper.toResponse(savedUser);
        
        log.info("User created successfully with ID: {}", savedUser.getId());
        return response;
    }
    
    /**
     * Retrieves a user by their ID.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add caching for frequently accessed users
     * - Implement user access logging
     * - Add security checks for user access
     */
    @Override
    public Optional<UserResponse> findById(Long id) {
        log.debug("Finding user by ID: {}", id);
        
        if (id == null) {
            return Optional.empty();
        }
        
        return userRepository.findById(id)
                .map(userMapper::toResponse);
    }
    
    /**
     * Retrieves a user by their email address.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add email validation
     * - Implement case-insensitive search optimization
     * - Add security logging for email lookups
     */
    @Override
    public Optional<UserResponse> findByEmail(String email) {
        log.debug("Finding user by email: {}", email);
        
        if (email == null || email.trim().isEmpty()) {
            return Optional.empty();
        }
        
        return userRepository.findByEmail(email)
                .map(userMapper::toResponse);
    }
    
    /**
     * Retrieves all users in the system.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add filtering options (active only, by role, etc.)
     * - Implement sorting options
     * - Add result caching
     * - Consider memory implications for large datasets
     */
    @Override
    public List<UserResponse> findAll() {
        log.debug("Finding all users");
        
        List<User> users = userRepository.findAll();
        List<UserResponse> responses = users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
        
        log.debug("Found {} users", responses.size());
        return responses;
    }
    
    /**
     * Retrieves all users with pagination.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add comprehensive parameter validation
     * - Implement cursor-based pagination
     * - Add total count and page metadata
     * - Add sorting options
     */
    @Override
    public List<UserResponse> findAll(int page, int size) {
        log.debug("Finding users with pagination - page: {}, size: {}", page, size);
        
        // Basic parameter validation
        if (page < 0) page = 0;
        if (size <= 0) size = 10;
        
        int offset = page * size;
        
        List<User> users = userRepository.findAll(offset, size);
        return users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * Updates an existing user.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add field-level validation
     * - Implement optimistic locking
     * - Add audit logging for changes
     * - Implement partial update validation
     * - Add email change verification
     */
    @Override
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        log.info("Updating user with ID: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        
        if (request == null) {
            throw new IllegalArgumentException("Update request cannot be null");
        }
        
        // Find existing user
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        
        // Check email uniqueness if email is being updated
        if (request.getEmail() != null && !request.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Email " + request.getEmail() + " is already in use");
            }
        }
        
        // Update entity using mapper
        userMapper.updateEntityFromRequest(request, existingUser);
        
        // Save updated user
        User savedUser = userRepository.save(existingUser);
        
        // Convert to response DTO
        UserResponse response = userMapper.toResponse(savedUser);
        
        log.info("User updated successfully with ID: {}", id);
        return response;
    }
    
    /**
     * Deletes a user by ID (soft delete recommended).
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Implement soft delete instead of hard delete
     * - Add cascade deletion for related entities
     * - Add confirmation mechanisms
     * - Implement audit logging
     * - Add restoration functionality
     */
    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user with ID: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        
        // Check if user exists
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        
        // Perform hard delete for simplicity - students can implement soft delete
        boolean deleted = userRepository.deleteById(id);
        
        if (deleted) {
            log.info("User deleted successfully with ID: {}", id);
        } else {
            log.warn("Failed to delete user with ID: {}", id);
        }
    }
    
    /**
     * Activates a user account.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add activation email notification
     * - Implement activation audit logging
     * - Add activation validation rules
     */
    @Override
    public void activateUser(Long id) {
        log.info("Activating user with ID: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        
        user.setActive(true);
        userRepository.save(user);
        
        log.info("User activated successfully with ID: {}", id);
    }
    
    /**
     * Deactivates a user account.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add deactivation email notification
     * - Implement deactivation audit logging
     * - Add deactivation validation rules
     */
    @Override
    public void deactivateUser(Long id) {
        log.info("Deactivating user with ID: {}", id);
        
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
        
        user.setActive(false);
        userRepository.save(user);
        
        log.info("User deactivated successfully with ID: {}", id);
    }
    
    /**
     * Checks if a user exists by ID.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add caching for existence checks
     * - Implement batch existence checks
     */
    @Override
    public boolean existsById(Long id) {
        log.debug("Checking if user exists with ID: {}", id);
        
        if (id == null) {
            return false;
        }
        
        return userRepository.existsById(id);
    }
    
    /**
     * Checks if a user exists by email.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add email validation
     * - Implement case-insensitive checks
     * - Add email domain validation
     */
    @Override
    public boolean existsByEmail(String email) {
        log.debug("Checking if user exists with email: {}", email);
        
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        return userRepository.existsByEmail(email);
    }
    
    /**
     * Counts total number of users.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add filtering options (active only, by role, etc.)
     * - Implement caching for count operations
     * - Add count by date range
     */
    @Override
    public long countUsers() {
        log.debug("Counting total users");
        
        return userRepository.count();
    }
    
    /**
     * Searches users by name.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add fuzzy search capabilities
     * - Implement full-text search
     * - Add search result ranking
     * - Add search pagination
     */
    @Override
    public List<UserResponse> searchByName(String searchTerm) {
        log.debug("Searching users by name: {}", searchTerm);
        
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return List.of();
        }
        
        List<User> users = userRepository.findByNameContaining(searchTerm);
        return users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    // TODO: Add private helper methods for common operations
    // TODO: Add validation helper methods
    // TODO: Add logging helper methods
    // TODO: Add password hashing helper methods
    
    /**
     * Private helper method to validate user ID.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add ID range validation
     * - Implement custom validation rules
     * - Add logging for validation failures
     */
    private void validateUserId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        
        if (id <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
    }
    
    /**
     * Private helper method to validate email format.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Add comprehensive email validation
     * - Implement domain validation
     * - Add email format normalization
     */
    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        
        // Basic email format validation
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Invalid email format");
        }
    }
    
    /**
     * Private helper method to hash password.
     * 
     * ENHANCEMENT OPPORTUNITIES for students:
     * - Implement BCrypt password hashing
     * - Add password strength validation
     * - Add salt generation
     * - Implement password history checking
     */
    private String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        // Basic "hashing" for template - students should implement proper hashing
        // In real application, use BCryptPasswordEncoder or similar
        return "hashed-" + plainPassword;
    }
}