# WorkingOut

## About
A specialized web application designed for fitness enthusiasts to manage their daily workout routines. The app allows users to create personalized exercise lists, track sets and reps, and organize their training by days of the week.

## Technologies used:
### Backend:  
- **Spring Boot** - main framework for creating this project  
- **Spring Data JPA** - communication with database  
- **Spring Validation** - to validate user input  
### Database:
- **PostgreSQL**
### Frontend:
- **Htmx** - for simplified CRUD operations  
- **Thymeleaf** - template engine  
### Testing:
- **JUnit5** - unit testing  
- **Mockito** - mocking dependencies  
## Usage
### Requirements
- Java SDK 25
- PostgreSQL
1. Create Database "workingout"
2. Create new file in **src/main/resources/application-local.properties**  
```
#Change with your db configuration
DB_USERNAME=YOUR_DB_USERNAME
DB_PASSWORD=YOUR_DB_PASSWORD
```
The application will be available at: http://localhost:8080
