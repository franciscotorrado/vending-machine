# Vending Machine - POC

_Author: Francisco José Torrado Ruiz_

Date: Wednesday, 23rd February 2022

### Summary

- [Content](#Content)
- [Requirements](#Requirements)
- [TDD](#TDD)
- [Documentation](#Documentation)
- [Components and Libraries](#Components)
- [Execution](#Execution)

## Content

I was proposed to develop a Vending Machine.

I have decided to use hexagonal architecture because, in my opinion, is a good way to separate de machine behaviour from
the exposed API implementation. I've asked to expose the interactions thought an API REST, but it could be, in the
future, a command line, USB port, etc.

## Requirements

Design an API for a vending machine, allowing users with a “seller” role to add, update
or remove products, while users with a “buyer” role can deposit coins into the machine
and make purchases. Your vending machine should only accept 5, 10, 20, 50 and 100
cent coins

### Tasks:
 - REST API should be implemented consuming and producing “application/json”
 - Implement product model with amountAvailable, cost (should be in multiples of 5), productName and sellerId fields
 - Implement user model with username, password, deposit and role fields
 - Implement an authentication method (basic, oAuth, JWT or something else, the choice is yours)
 - All of the endpoints should be authenticated unless stated otherwise
 - Implement CRUD for users (POST /user should not require authentication to allow new user registration)
 - Implement CRUD for a product model (GET can be called by anyone, while POST, PUT and DELETE can be called only by the seller user who created the product)
 - Implement /deposit endpoint so users with a “buyer” role can deposit only 5, 10, 20, 50 and 100 cent coins into their vending machine account (one coin at the time)
 - Implement /buy endpoint (accepts productId, amount of products) so users with a “buyer” role can buy a product (shouldn't be able to buy multiple different products at the same time) with the money they’ve deposited. API should return total they’ve spent, the product they’ve purchased and their change if there’s any (in an array of 5, 10, 20, 50 and 100 cent coins)
 - Implement /reset endpoint so users with a “buyer” role can reset their deposit back to 0
 - Take time to think about possible edge cases and access issues that should be solved

### Evaluation criteria:
 - Language/Framework of choice best practices
 - Edge cases covered
 - Write tests for /deposit, /buy and one CRUD endpoint of your choice
 - Code readability and optimization

### Bonus:
 - If somebody is already logged in with the same credentials, the user should be given a message "There is already an active session using your account". In this case the user should be able to terminate all the active sessions on their account via an endpoint i.e. /logout/all
 - Attention to security

### Deliverables:
 - A Github repository with public access. Please have the solution running on your computer so the domain expert can tell you which tests to run. Please have a Postman collection ready on your computer so the domain expert can tell you what tests to run on the API.

## TDD

I have used AssertJ library to improve semantic in test assertions and results.

## Documentation

I will incorporate Swagger to document the API-REST. It will be accessible at:

http://localhost:8080/swagger-ui/index.html

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
- OpenAPI Documentation

## Execution

You can run application and tests with these commands (Using Gradle scripts in the root of the project):

Tests:

``
gradlew test
``

Run:

``
gradlew bootRun
``

H2 Database Console:

http://localhost:8080/h2-console

