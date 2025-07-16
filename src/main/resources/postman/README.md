# Postman Collection for Ticket Booking API

This directory contains Postman collections and environments for testing the Ticket Booking REST API.

## Files

- `Ticket-Booking-API.postman_collection.json` - Main API collection with all endpoints
- `README.md` - This file with usage instructions

## Getting Started

### 1. Import the Collection

1. Open Postman
2. Click "Import" button
3. Select `Ticket-Booking-API.postman_collection.json`
4. The collection will be imported with all endpoints

### 2. Set Environment Variables

The collection uses the following variables:

| Variable | Default Value | Description |
|----------|---------------|-------------|
| `baseUrl` | `http://localhost:8080` | Base URL for the API |
| `apiVersion` | `v1` | API version |
| `userId` | `1` | User ID for testing |
| `userEmail` | `test@example.com` | Test email address |

### 3. Test Endpoints

The collection includes the following endpoint categories:

#### User Management
- **POST** `/api/v1/users` - Create a new user
- **GET** `/api/v1/users/{id}` - Get user by ID
- **GET** `/api/v1/users` - Get all users (with pagination)
- **PUT** `/api/v1/users/{id}` - Update user
- **DELETE** `/api/v1/users/{id}` - Delete user

#### User Status Management
- **PATCH** `/api/v1/users/{id}/activate` - Activate user
- **PATCH** `/api/v1/users/{id}/deactivate` - Deactivate user

#### User Search and Analytics
- **GET** `/api/v1/users/search?q={searchTerm}` - Search users
- **GET** `/api/v1/users/count` - Get user count

#### Error Scenarios
- Invalid email validation
- Missing required fields
- User not found scenarios

#### Health Check
- **GET** `/api/v1/users/health` - API health check

## Usage Instructions

### 1. Start the Application

```bash
# Start the Spring Boot application
./mvnw spring-boot:run

# Or run the JAR file
java -jar target/ticket-booking-system-0.0.1-SNAPSHOT.jar
```

### 2. Test the Happy Path

1. **Create a User**: Use the "Create User" request with valid data
2. **Get the User**: Use the "Get User by ID" request with the returned ID
3. **Update the User**: Use the "Update User" request to modify user data
4. **List Users**: Use the "Get All Users" request to see all users
5. **Delete the User**: Use the "Delete User" request to remove the user

### 3. Test Error Scenarios

1. **Invalid Email**: Try creating a user with an invalid email format
2. **Missing Fields**: Try creating a user without required fields
3. **User Not Found**: Try getting a user with a non-existent ID
4. **Duplicate Email**: Try creating two users with the same email

### 4. Test Status Management

1. **Deactivate User**: Use the "Deactivate User" request
2. **Activate User**: Use the "Activate User" request
3. **Verify Status**: Check user status in the response

## Expected Response Formats

### Success Response (User)
```json
{
  "id": 1,
  "email": "test@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": "+1234567890",
  "active": true,
  "createdAt": "2024-01-01 12:00:00",
  "updatedAt": "2024-01-01 12:00:00"
}
```

### Error Response
```json
{
  "code": "VALIDATION_ERROR",
  "message": "Validation failed",
  "timestamp": "2024-01-01 12:00:00",
  "details": {
    "email": "Invalid email format",
    "password": "Password is required"
  }
}
```

## HTTP Status Codes

| Status Code | Description | When Used |
|-------------|-------------|-----------|
| 200 OK | Success | GET, PUT, PATCH operations |
| 201 Created | Resource created | POST operations |
| 204 No Content | Success with no body | DELETE operations |
| 400 Bad Request | Validation error | Invalid request data |
| 404 Not Found | Resource not found | Non-existent resource |
| 409 Conflict | Resource conflict | Duplicate email |
| 500 Internal Server Error | Server error | Unexpected errors |

## Testing Best Practices

### 1. Use Environment Variables

- Set up different environments (dev, test, prod)
- Use variables for dynamic values (IDs, URLs)
- Don't hardcode values in requests

### 2. Add Test Scripts

```javascript
// Test for successful response
pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

// Test response structure
pm.test("Response has required fields", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData).to.have.property('id');
    pm.expect(jsonData).to.have.property('email');
});

// Extract variables for next requests
pm.test("Extract user ID", function () {
    var jsonData = pm.response.json();
    pm.environment.set("userId", jsonData.id);
});
```

### 3. Test Data Management

- Use realistic test data
- Clean up test data after testing
- Use consistent data formats
- Test edge cases and boundary conditions

### 4. Error Testing

- Test all error scenarios
- Verify error response format
- Check error messages are helpful
- Test input validation thoroughly

## TODO for Students

1. **Complete the Implementation**: Implement all the endpoint stubs in the controllers
2. **Add Test Scripts**: Add JavaScript test scripts to validate responses
3. **Create Environment Files**: Create separate environments for different stages
4. **Test All Scenarios**: Test both happy path and error scenarios
5. **Document Findings**: Document any issues or improvements needed
6. **Add More Tests**: Add additional test cases for edge cases

## Troubleshooting

### Common Issues

1. **Connection Refused**: Make sure the application is running on port 8080
2. **404 Not Found**: Check if the endpoint URL is correct
3. **500 Internal Server Error**: Check application logs for details
4. **Validation Errors**: Verify request body format and required fields

### Debug Tips

1. Check the Console tab in Postman for errors
2. Use the Postman Console to see request/response details
3. Check application logs for server-side errors
4. Verify Content-Type headers are set correctly

## Resources

- [Postman Documentation](https://learning.postman.com/)
- [REST API Best Practices](https://restfulapi.net/)
- [Spring Boot Testing Guide](https://spring.io/guides/gs/testing-web/)
- [HTTP Status Codes](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status)

## Assignment Requirements

This Postman collection should be used to:

1. Test all implemented endpoints
2. Verify proper HTTP status codes
3. Validate request/response formats
4. Test error handling
5. Document API behavior
6. Demonstrate REST best practices

Make sure to export and submit your updated collection after completing the implementation!