# Vending Machine - POC 
_Author: Francisco J. Torrado_

Date: Thursday, 17th February 2022

### Summary
- [Content](#Content)
- [TDD](#TDD)
- [Documentation](#Documentation)
- [Components and Libraries](#Components)
- [Execution](#Execution)


## Content

I was proposed to develop a Vending Machine.

I have decided to use hexagonal architecture because, in my opinion, is a good way to separate de machine behaviour from 
the exposed API implementation. I've asked to expose the interactions thought an API REST, but it could be, in the future, 
a command line, USB port, etc. 

## TDD

I have used AssertJ library to improve semantic in test assertions and results.

## Documentation

I will incorporate Swagger to document the API-REST. It will be accessible at:

http://localhost:8080/swagger-ui.html

It allows to check the contract and try the controller with real operations.

## Components

The project scaffolding has being created used Spring Initializr choosing these components:
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring Web
- Spring Actuator
- Flyway
- Lombok
- H2 Database
- Spring Contract Test
- Swagger

## Execution

You can run application and tests with these commands (Using Gradle scripts in the root of the project):

Tests:

gradlew test

Run:

gradlew bootRun


