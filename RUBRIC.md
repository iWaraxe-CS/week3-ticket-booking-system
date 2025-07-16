# Grading Rubric - Ticket Booking REST API Foundation

**Total Points: 100**

## API Design (25 points)

### RESTful Principles (10 points)
- **Resource Naming** (3 points)
    - ✓ Plural nouns for collections (/users)
    - ✓ No verbs in URLs
    - ✓ Hierarchical structure where appropriate

- **HTTP Methods** (4 points)
    - ✓ GET for retrieval (1 point)
    - ✓ POST for creation (1 point)
    - ✓ PUT/PATCH for updates (1 point)
    - ✓ DELETE for removal (1 point)

- **HTTP Status Codes** (3 points)
    - ✓ 200 OK for successful GET/PUT
    - ✓ 201 Created for successful POST
    - ✓ 404 Not Found for missing resources
    - ✓ 400 Bad Request for validation errors

### API Consistency (8 points)
- **Response Format** (4 points)
    - ✓ Consistent JSON structure (2 points)
    - ✓ Proper use of DTOs (2 points)

- **URL Patterns** (2 points)
    - ✓ Versioning implemented (/api/v1)
    - ✓ Consistent path structure

- **Pagination** (2 points)
    - ✓ Implemented for list endpoints
    - ✓ Standard pagination parameters

### Request/Response Design (7 points)
- **Request DTOs** (3 points)
    - ✓ CreateUserRequest properly structured
    - ✓ UpdateUserRequest with partial updates
    - ✓ No entity exposure

- **Response DTOs** (2 points)
    - ✓ UserResponse with appropriate fields
    - ✓ Consistent date/time formatting

- **Error Responses** (2 points)
    - ✓ Structured error format
    - ✓ Meaningful error messages

## Architecture (25 points)

### Layer Separation (12 points)
- **Controller Layer** (4 points)
    - ✓ Thin controllers (1 point)
    - ✓ Proper request mapping (1 point)
    - ✓ No business logic (1 point)
    - ✓ Proper dependency injection (1 point)

- **Service Layer** (4 points)
    - ✓ Business logic encapsulated (2 points)
    - ✓ Interface and implementation separated (2 points)

- **Repository Layer** (4 points)
    - ✓ Data access abstraction (2 points)
    - ✓ In-memory storage implementation (2 points)

### Spring Patterns (8 points)
- **Dependency Injection** (4 points)
    - ✓ Constructor injection used (2 points)
    - ✓ @RequiredArgsConstructor or explicit constructors (1 point)
    - ✓ No field injection (1 point)

- **Annotations** (4 points)
    - ✓ Proper use of @RestController (1 point)
    - ✓ @Service annotation on services (1 point)
    - ✓ @Repository on repositories (1 point)
    - ✓ @Component where appropriate (1 point)

### Data Mapping (5 points)
- **MapStruct Implementation** (3 points)
    - ✓ Mapper interfaces defined (1 point)
    - ✓ Entity to DTO mappings (1 point)
    - ✓ Update mappings for partial updates (1 point)

- **Mapping Quality** (2 points)
    - ✓ No manual mapping code
    - ✓ Null handling configured

## Code Quality (20 points)

### Validation (8 points)
- **Input Validation** (5 points)
    - ✓ Email validation (1 point)
    - ✓ Password strength validation (1 point)
    - ✓ Required field validation (1 point)
    - ✓ Custom validation rules (1 point)
    - ✓ Phone number format (1 point)

- **Validation Integration** (3 points)
    - ✓ @Valid annotation usage (1 point)
    - ✓ Validation groups if needed (1 point)
    - ✓ Custom validators if applicable (1 point)

### Exception Handling (7 points)
- **Global Exception Handler** (4 points)
    - ✓ @ControllerAdvice implementation (2 points)
    - ✓ Multiple exception types handled (2 points)

- **Error Response Quality** (3 points)
    - ✓ Consistent error format (1 point)
    - ✓ Field-level validation errors (1 point)
    - ✓ Helpful error messages (1 point)

### Code Standards (5 points)
- **Java Conventions** (2 points)
    - ✓ Proper naming conventions
    - ✓ Package organization

- **Clean Code** (3 points)
    - ✓ No code duplication (1 point)
    - ✓ Small, focused methods (1 point)
    - ✓ Meaningful variable names (1 point)

## Testing (15 points)

### Unit Tests (6 points)
- **Service Tests** (3 points)
    - ✓ All service methods tested
    - ✓ Edge cases covered
    - ✓ Mocking used appropriately

- **Repository Tests** (3 points)
    - ✓ CRUD operations tested
    - ✓ In-memory storage verified
    - ✓ Concurrent access handled

### Integration Tests (6 points)
- **Controller Tests** (4 points)
    - ✓ MockMvc setup correctly (1 point)
    - ✓ All endpoints tested (2 points)
    - ✓ Status codes verified (1 point)

- **Validation Tests** (2 points)
    - ✓ Invalid input scenarios
    - ✓ Validation error responses checked

### Test Quality (3 points)
- **Coverage** (2 points)
    - ✓ Minimum 80% line coverage
    - ✓ Critical paths fully tested

- **Test Organization** (1 point)
    - ✓ Clear test names
    - ✓ Given-When-Then structure

## Error Handling (10 points)

### Exception Types (4 points)
- **Custom Exceptions** (2 points)
    - ✓ ResourceNotFoundException
    - ✓ ValidationException

- **Exception Hierarchy** (2 points)
    - ✓ Appropriate inheritance
    - ✓ Meaningful exception messages

### Error Responses (6 points)
- **Response Structure** (3 points)
    - ✓ Error code field (1 point)
    - ✓ User-friendly message (1 point)
    - ✓ Timestamp included (1 point)

- **Validation Errors** (3 points)
    - ✓ Field-level error details (2 points)
    - ✓ Multiple errors returned (1 point)

## Documentation (5 points)

### Postman Collection (3 points)
- **Completeness** (2 points)
    - ✓ All endpoints included
    - ✓ Success and error examples
    - ✓ Environment variables used

- **Organization** (1 point)
    - ✓ Logical folder structure
    - ✓ Clear request names

### Code Documentation (2 points)
- **API Documentation** (1 point)
    - ✓ Endpoint purposes clear
    - ✓ Complex logic explained

- **Configuration** (1 point)
    - ✓ Application properties documented
    - ✓ Profile usage explained

## Bonus Points (Maximum 10 points)

### Advanced Features (+5 points)
- **Custom Configuration** (+2 points)
    - ✓ Custom Spring beans
    - ✓ Configuration properties class

- **Advanced Validation** (+2 points)
    - ✓ Cross-field validation
    - ✓ Conditional validation

- **Soft Delete** (+1 point)
    - ✓ Implemented instead of hard delete

### Technical Excellence (+5 points)
- **HATEOAS** (+2 points)
    - ✓ Links in responses
    - ✓ Proper resource navigation

- **Advanced MapStruct** (+2 points)
    - ✓ Custom mapping methods
    - ✓ Expression mappings

- **Logging** (+1 point)
    - ✓ Structured logging
    - ✓ Appropriate log levels

## Deductions

### Major Issues (-5 to -10 points each)
- **Not RESTful**: -10 points (verbs in URLs, wrong methods)
- **Returning Entities**: -10 points (no DTO usage)
- **No Exception Handling**: -10 points
- **Compilation Errors**: -10 points
- **Missing CRUD Operations**: -5 points per operation

### Minor Issues (-1 to -3 points each)
- **Inconsistent Naming**: -2 points
- **Wrong Status Codes**: -1 point per endpoint
- **Missing Validation**: -2 points per field
- **No Postman Collection**: -3 points
- **Poor Test Coverage**: -1 point per 10% below 80%

## Grade Scale

- **A (90-100 points)**: Professional REST API with excellent Spring practices
- **B (80-89 points)**: Good implementation with minor REST or Spring issues
- **C (70-79 points)**: Adequate API with some design or architectural problems
- **D (60-69 points)**: Basic implementation with significant issues
- **F (Below 60 points)**: Incomplete or non-RESTful implementation

## Submission Checklist

Before submitting, ensure:
- [ ] All CRUD operations implemented
- [ ] RESTful URLs (no verbs)
- [ ] Proper HTTP methods used
- [ ] Correct status codes returned
- [ ] DTOs used (no entity exposure)
- [ ] MapStruct mappers working
- [ ] Validation implemented
- [ ] Global exception handler configured
- [ ] All tests passing
- [ ] 80%+ test coverage
- [ ] Postman collection complete
- [ ] Environment variables documented
- [ ] Clean Maven build
- [ ] Application runs on port 8080

## Late Submission Policy

- **1 day late**: -5 points
- **2 days late**: -10 points
- **3 days late**: -15 points
- **More than 3 days late**: Assignment not accepted

## Academic Integrity

- You may reference Spring documentation
- You may use Spring guides as examples
- You may discuss REST principles with classmates
- You may NOT copy controller implementations
- You may NOT share service logic
- All code must demonstrate understanding
- Cite any external REST utilities used

## Feedback Process

After grading, you will receive:
- REST API design analysis
- Spring best practices review
- Architecture assessment
- Suggestions for production readiness
- Performance considerations

## Special Considerations

### ASP.NET Core Comparison Credit
- Extra recognition for documented comparisons
- Bonus for identifying Spring advantages
- Credit for explaining DI differences

### API Design Excellence
- Recognition for consistent API design
- Bonus for advanced REST patterns
- Credit for excellent error handling

**Note**: This assignment establishes your REST API foundation. Future assignments will build upon this base, adding databases, security, and advanced features. Focus on getting the fundamentals right.