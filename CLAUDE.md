# Week 3 Assignment: Ticket Booking REST API Foundation - Template claude.md

@~/.claude/dotnet-to-java-system-guide.md

@~/.claude/dotnet-to-java-course.md

@~/.claude/project-type-homework-template.md

## Assignment Overview
Build the foundation of a ticket booking REST API using Spring Boot. Create a properly layered application with controllers, services, and repositories while comparing Spring patterns with ASP.NET Core.

## Learning Objectives
- Build REST APIs with Spring Boot
- Implement Spring's dependency injection
- Use proper layered architecture
- Apply DTO pattern with MapStruct
- Handle exceptions globally
- Write integration tests with MockMvc
- Document APIs with Postman

## Project Structure
```
ticket-booking-api/
├── pom.xml
├── README.md
├── RUBRIC.md
├── COMPARISON.md          # ASP.NET Core vs Spring Boot
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── ticketbooking/
│   │   │           ├── TicketBookingApplication.java
│   │   │           ├── controller/
│   │   │           ├── service/
│   │   │           ├── repository/
│   │   │           ├── dto/
│   │   │           ├── entity/
│   │   │           ├── mapper/
│   │   │           ├── exception/
│   │   │           └── config/
│   │   └── resources/
│   │       ├── application.yml
│   │       └── postman/
│   └── test/
│       └── java/
└── docs/
    └── api-design.md
```

## Provided Stubs

### Application Entry Point
```java
package com.ticketbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketBookingApplication.class, args);
        // Compare with ASP.NET Core Program.cs
    }
}
```

### Controller Stub
```java
package com.ticketbooking.controller;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    // TODO: Inject UserService
    // Compare with ASP.NET Core [ApiController]
    
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        // TODO: Implement
        // Hint: Return ResponseEntity for status control
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        // TODO: Implement
        // Compare with [HttpGet("{id}")]
    }
    
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        // TODO: Implement
        // Note: @Valid triggers validation
        // Return 201 Created with location header
    }
}
```

### Service Interface
```java
package com.ticketbooking.service;

public interface UserService {
    List<UserResponse> findAll();
    Optional<UserResponse> findById(Long id);
    UserResponse create(CreateUserRequest request);
    UserResponse update(Long id, UpdateUserRequest request);
    void deleteById(Long id);
}

// Implementation stub
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    // TODO: Inject UserRepository and UserMapper
    // Compare with .NET service registration
}
```

### Repository Interface
```java
package com.ticketbooking.repository;

public interface UserRepository {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);
    boolean existsByEmail(String email);
}

// In-memory implementation
@Repository
public class InMemoryUserRepository implements UserRepository {
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);
    
    // TODO: Implement thread-safe CRUD operations
    // Note: No database yet, just in-memory
}
```

### Entity and DTOs
```java
// Entity
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
    private boolean active = true;
}

// Request DTOs
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*?&]+$",
             message = "Password must contain letters and numbers")
    private String password;
    
    // TODO: Add other fields with validation
}

// Response DTO
@Data
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String createdAt;
    // Note: No password in response!
}
```

### MapStruct Mapper
```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    // TODO: Configure mappings
    UserResponse toResponse(User user);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
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
        // TODO: Build error response
        // Compare with ASP.NET Core exception filters
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        // TODO: Extract field errors
        // Build detailed validation error response
    }
}

// Custom exceptions
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

// Error response DTO
@Data
@Builder
public class ErrorResponse {
    private String code;
    private String message;
    private LocalDateTime timestamp;
    private Map<String, String> details;
}
```

### Test Stubs
```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void should_CreateUser_When_ValidRequest() throws Exception {
        // TODO: Test POST /api/v1/users
        // Compare with ASP.NET Core TestServer
        
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "email": "test@example.com",
                        "password": "Test123!",
                        "firstName": "John",
                        "lastName": "Doe"
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists());
    }
    
    @Test
    void should_ReturnBadRequest_When_InvalidEmail() throws Exception {
        // TODO: Test validation
    }
}

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    
    @Mock
    private UserMapper userMapper;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    @Test
    void should_CreateUser_When_EmailNotExists() {
        // TODO: Unit test with mocks
        // Compare with Moq in .NET
    }
}
```

## Requirements Checklist

### Functional Requirements
- [ ] GET /api/v1/users - List all users
- [ ] GET /api/v1/users/{id} - Get user by ID
- [ ] POST /api/v1/users - Create user
- [ ] PUT /api/v1/users/{id} - Update user
- [ ] DELETE /api/v1/users/{id} - Delete user
- [ ] Pagination for list endpoint
- [ ] Email uniqueness validation

### Technical Requirements
- [ ] Spring Boot 3.x setup
- [ ] Layered architecture
- [ ] Constructor injection
- [ ] DTO pattern implementation
- [ ] MapStruct configuration
- [ ] Global exception handling
- [ ] Bean validation

### Testing Requirements
- [ ] Controller integration tests
- [ ] Service unit tests
- [ ] Repository tests
- [ ] Validation tests
- [ ] Error handling tests
- [ ] 80% code coverage

### Documentation Requirements
- [ ] Postman collection
- [ ] Environment variables
- [ ] Error examples
- [ ] API versioning
- [ ] README updates

## Implementation Hints

### REST Best Practices
```java
// DON'T: Verbs in URLs
@GetMapping("/getUser/{id}")  // Wrong!

// DO: Resource-based URLs
@GetMapping("/{id}")  // Correct

// DON'T: Return entities
return userRepository.findById(id);  // Exposes internals!

// DO: Return DTOs
return userMapper.toResponse(user);  // Safe
```

### Status Code Guidelines
```java
// CREATE: Return 201 with Location header
@PostMapping
public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
    UserResponse created = userService.create(request);
    URI location = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(created.getId())
        .toUri();
    return ResponseEntity.created(location).body(created);
}

// UPDATE: Return 200 or 204
// DELETE: Return 204 No Content
// NOT FOUND: Return 404
```

### Validation Patterns
```java
// Field-level validation
@Email
@NotBlank
private String email;

// Cross-field validation
@ValidPassword  // Custom validator
private String password;

// Conditional validation
@NotNull(groups = UpdateValidation.class)
private Long id;
```

### Spring vs ASP.NET Core
```java
// ASP.NET Core: Startup.cs / Program.cs
// services.AddScoped<IUserService, UserService>();

// Spring: Automatic with @Service
@Service  // Automatically registered
public class UserService {
    // No manual registration needed
}

// ASP.NET Core: [FromServices]
// Spring: Constructor injection by default
```

## Grading Focus Areas

1. **REST Design (25%)**
    - Proper resource URLs
    - Correct HTTP methods
    - Appropriate status codes
    - Consistent responses

2. **Architecture (25%)**
    - Clean layer separation
    - Dependency injection
    - DTO usage
    - No business logic in controllers

3. **Code Quality (20%)**
    - Spring conventions
    - Validation implementation
    - Exception handling
    - Clean code principles

4. **Testing (15%)**
    - Integration tests
    - Unit tests
    - Test coverage
    - Edge cases

5. **Error Handling (10%)**
    - Global handler
    - Consistent format
    - Helpful messages
    - Validation details

6. **Documentation (5%)**
    - Postman collection
    - Clear endpoints
    - Error examples

## Common Mistakes to Avoid
- Returning entities instead of DTOs
- Using verbs in REST URLs
- Field injection (@Autowired on fields)
- Missing validation on requests
- Inconsistent error responses
- Not testing error cases
- Hardcoding values
- Missing pagination
- Wrong HTTP status codes
- Exposing internal exceptions

## Bonus Challenges (+10 points max)
- Implement HATEOAS links
- Add OpenAPI documentation
- Create custom validators
- Implement soft delete
- Add request/response logging
- Create API versioning strategy
- Add rate limiting
- Implement search filters

## Resources Allowed
- Spring Boot documentation
- Spring guides and tutorials
- MapStruct documentation
- REST best practices guides
- Postman documentation
- Course materials

## Submission Requirements
1. Fork the template repository
2. Create feature branch
3. Implement all requirements
4. Export Postman collection
5. Update all documentation
6. Ensure tests pass
7. Submit pull request by deadline

## Configuration Files

### application.yml
```yaml
spring:
  application:
    name: ticket-booking-api
    
server:
  port: 8080
  
# TODO: Add logging configuration
# TODO: Add custom properties
```

### pom.xml dependencies
```xml
<!-- Provided dependencies -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
</dependency>
<!-- TODO: Check versions and processors -->
```