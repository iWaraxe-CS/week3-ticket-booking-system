# Week 3 Assignment: Ticket Booking REST API Foundation

## Overview
Build the foundation of a ticket booking system using Spring Boot and REST principles. This assignment introduces Spring's web framework, dependency injection, and proper API design patterns while drawing parallels to ASP.NET Core concepts you already know.

## Learning Objectives
- Create RESTful APIs using Spring Boot (compare with ASP.NET Core)
- Implement proper layered architecture with Spring
- Master Spring's dependency injection container
- Use DTOs and MapStruct for data mapping
- Apply REST principles and HTTP best practices
- Test APIs with Postman
- Configure Spring Boot applications

## Assignment Requirements

### Functional Requirements
Your Ticket Booking API must support:

- **User Management**
    - Create new users with validation
    - Retrieve user by ID
    - Update user information
    - Delete users (soft delete preferred)
    - List all users with pagination

- **Data Validation**
    - Email format validation
    - Password strength requirements
    - Phone number format
    - Required field validation
    - Custom validation rules

- **Error Handling**
    - Consistent error response format
    - Proper HTTP status codes
    - Validation error details
    - Global exception handling

- **API Documentation**
    - Comprehensive Postman collection
    - Example requests and responses
    - Environment variables setup
    - Error scenario examples

### Technical Requirements

- **Framework**: Spring Boot 3.x
- **Java Version**: Java 21 (LTS)
- **Build Tool**: Maven
- **Architecture**: Controller → Service → Repository layers
- **Storage**: In-memory storage (no database yet)
- **Mapping**: MapStruct for DTO-Entity conversion
- **Validation**: Jakarta Bean Validation
- **Testing**: Spring Boot Test + MockMvc

### Code Quality Standards

- Follow RESTful naming conventions
- Use proper HTTP methods (GET, POST, PUT, DELETE)
- Implement DTOs for request/response
- Keep entities separate from DTOs
- Use constructor injection
- Write self-documenting endpoints
- Apply single responsibility principle

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── ticketbookingsystem/
│   │           ├── TicketBookingSystemApplication.java
│   │           ├── controller/
│   │           │   ├── UserController.java
│   │           │   └── GlobalExceptionHandler.java
│   │           ├── service/
│   │           │   ├── UserService.java
│   │           │   └── impl/
│   │           │       └── UserServiceImpl.java
│   │           ├── repository/
│   │           │   ├── UserRepository.java
│   │           │   └── impl/
│   │           │       └── InMemoryUserRepository.java
│   │           ├── dto/
│   │           │   ├── request/
│   │           │   │   ├── CreateUserRequest.java
│   │           │   │   └── UpdateUserRequest.java
│   │           │   └── response/
│   │           │       ├── UserResponse.java
│   │           │       └── ErrorResponse.java
│   │           ├── entity/
│   │           │   └── User.java
│   │           ├── mapper/
│   │           │   └── UserMapper.java
│   │           ├── exception/
│   │           │   ├── ResourceNotFoundException.java
│   │           │   └── ValidationException.java
│   │           └── config/
│   │               ├── MapStructConfig.java
│   │               └── ApplicationConfig.java
│   └── resources/
│       ├── application.yml
│       ├── application-dev.yml
│       └── postman/
│           └── Ticket-Booking-API.postman_collection.json
└── test/
    ├── java/
    │   └── com/
    │       └── ticketbooking/
    │           ├── controller/
    │           │   └── UserControllerTest.java
    │           ├── service/
    │           │   └── UserServiceTest.java
    │           └── integration/
    │               └── UserApiIntegrationTest.java
    └── resources/
        └── application-test.yml
```

## Getting Started

1. **Clone the Repository**
   ```bash
   git clone <repository-url>
   cd dotnet-to-java-week3-homework-template
   ```

2. **Build the Project**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn spring-boot:run
   # Or
   java -jar target/ticket-booking-api-0.0.1-SNAPSHOT.jar
   ```

4. **Test the API**
   ```bash
   # Import Postman collection from src/main/resources/postman/
   # Or use curl:
   curl -X GET http://localhost:8080/api/v1/users
   ```

5. **Run Tests**
   ```bash
   # All tests
   mvn test
   
   # Integration tests only
   mvn test -Dtest=*IntegrationTest
   ```

## Implementation Guidelines

### REST Endpoint Design
```java
// ASP.NET Core style:
// [ApiController]
// [Route("api/[controller]")]
// public class UsersController : ControllerBase

// Spring Boot style:
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    
    @GetMapping
    public List<UserResponse> getAllUsers() {
        // Implementation
    }
    
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        // Implementation
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        // Implementation
    }
}
```

### DTO vs Entity Pattern
```java
// Entity (Domain Model)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean active;
}

// DTO (Data Transfer Object)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String createdAt;
}
```

### MapStruct Configuration
```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
    User toEntity(CreateUserRequest request);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UpdateUserRequest request, @MappingTarget User user);
}
```

### Exception Handling
```java
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = ErrorResponse.builder()
            .code("NOT_FOUND")
            .message(ex.getMessage())
            .timestamp(LocalDateTime.now())
            .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        // Extract field errors
    }
}
```

### Validation Examples
```java
public class CreateUserRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]+$",
             message = "Password must contain letters and numbers")
    private String password;
    
    @NotBlank(message = "First name is required")
    private String firstName;
    
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", 
             message = "Invalid phone number format")
    private String phoneNumber;
}
```

## Key Java vs C# Differences to Document

Update your `COMPARISON.md` with:

1. **Spring Boot vs ASP.NET Core**
    - Annotation-based vs attribute routing
    - Dependency injection differences
    - Configuration approaches
    - Middleware vs filters/interceptors

2. **REST Controllers**
    - @RestController vs [ApiController]
    - Request mapping annotations
    - Response handling
    - Model binding differences

3. **Validation**
    - Bean Validation vs Data Annotations
    - Custom validators
    - Error response formatting
    - Validation groups

4. **Exception Handling**
    - @ControllerAdvice vs Exception filters
    - Global vs local handling
    - Error response standards

5. **Testing**
    - MockMvc vs TestServer
    - Integration test setup
    - Test data builders

## Deliverables

1. **Source Code**:
    - Complete REST API implementation
    - All CRUD operations for User
    - Proper layered architecture
    - Clean separation of concerns

2. **Tests**:
    - Unit tests for service layer
    - Integration tests for controllers
    - Validation tests
    - Error handling tests

3. **Documentation**:
    - Complete Postman collection
    - Environment setup guide
    - API endpoint documentation
    - C# to Java comparison notes

4. **Configuration**:
    - Spring Boot configuration
    - Multiple profiles (dev, test)
    - Logging configuration
    - Custom beans (optional)

## Postman Collection Requirements

Your Postman collection must include:

1. **Environment Variables**
    - {{baseUrl}} - http://localhost:8080
    - {{apiVersion}} - v1
    - Sample user data variables

2. **Request Organization**
    - User Management folder
    - Success scenarios
    - Error scenarios
    - Validation examples

3. **Test Scripts**
    - Status code validation
    - Response structure validation
    - Save response data for chaining

4. **Documentation**
    - Request descriptions
    - Parameter explanations
    - Example responses

## Grading Criteria

Your assignment will be evaluated on:
- **API Design** (25%): RESTful principles, proper HTTP usage
- **Architecture** (25%): Clean layering, separation of concerns
- **Code Quality** (20%): Spring best practices, clean code
- **Testing** (15%): Comprehensive test coverage
- **Error Handling** (10%): Proper exception handling
- **Documentation** (5%): Clear Postman collection

See `RUBRIC.md` for detailed point breakdown.

## Submission

1. Implement all requirements
2. Ensure all tests pass: `mvn test`
3. Export Postman collection to `src/main/resources/postman/`
4. Update documentation
5. Create pull request with summary
6. Include screenshots of Postman tests

## Tips for Success

- Start with entity and DTO design
- Implement one endpoint at a time
- Test each endpoint with Postman immediately
- Use Spring Boot DevTools for hot reload
- Compare with ASP.NET Core patterns
- Don't forget soft delete implementation
- Add meaningful logging statements
- Consider pagination early

## Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [REST Best Practices](https://restfulapi.net/)
- [MapStruct Documentation](https://mapstruct.org/documentation/stable/reference/html/)
- [Bean Validation](https://beanvalidation.org/2.0/spec/)
- [Postman Learning Center](https://learning.postman.com/)

## Common Pitfalls to Avoid

### API Design Issues
- Using verbs in URLs (/getUser instead of GET /users/{id})
- Returning entities directly instead of DTOs
- Inconsistent response formats
- Wrong HTTP status codes
- Missing validation

### Spring Boot Mistakes
- Field injection instead of constructor injection
- Not using @Service annotation
- Circular dependencies
- Not handling Optional properly
- Missing @Transactional where needed

### Testing Problems
- Only testing happy paths
- Not testing validation
- Missing integration tests
- Hard-coded test data
- Not cleaning up test state

Good luck building your first Spring Boot REST API!