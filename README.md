# Digital Chief test case

## Technical specification

Develop a client-server application that implements CRUD operations for Department and Employee entities.

Interaction with the application is performed using REST API.

### Entities:

* Department
    * Identifier
    * Name
    * Description
    * Location
* Employee
    * Identifier
    * First name
    * Patronymic
    * Last name
    * Job title
    * Email
    * Phone
    * Salary
    * Hire date
    * Department

### Relationships:

* A Department can have multiple Employee's working in it.
* An Employee can only work in one Department.

## Used technologies

- Java 17
- Spring Boot 3.3.1
- Hibernate
- Maven
- PostgreSQL
