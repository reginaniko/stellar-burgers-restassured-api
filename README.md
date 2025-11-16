# Stellar Burgers — API Automation Test Suite 
### REST Assured • JUnit4 • Allure • Java 11

<p align="left">
  <img src="https://img.shields.io/badge/Language-Java%2011-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Framework-Rest%20Assured-orange?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Test%20Runner-JUnit4-brightgreen?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Reporting-Allure%202-purple?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Build-Maven-yellow?style=for-the-badge" />
</p>


This repository contains a production-style **API automation test suite** for the Stellar Burgers backend that includes automated checks for:
- User registration and validation  
- Authentication and login workflows  
- Updating user information  
- Creating orders  
- Retrieving user order history  
- Positive and negative scenarios for all major flows  

# Purpose of the Project  
This automation framework validates the correctness, stability, and resilience of the Stellar Burgers backend API.  
It demonstrates:

- Strong understanding of API testing  
- Clean automation architecture  
- Working with tokens & auth flows  
- Negative case design  
- Data models and serialization  
- Professional Allure HTML reporting  

This repository is part of a larger **multi-layer QA portfolio** including UI automation, API automation, E2E testing, test strategy, and CI/CD pipelines.
Full QA portfolio: https://github.com/reginaniko/stellar-burgers


## Tech Stack

- **Java 11**
- **Rest Assured 5.3**
- **JUnit4**
- **AssertJ / Hamcrest**
- **Lombok**
- **GSON**
- **Allure**
- **Maven**
- **Java Faker**

## Project Structure

```bash
src/
 ├── main/java/
 │    ├── BaseHttpClient.java        # API client (GET/POST/PATCH/DELETE)
 │    ├── UserRequest.java           # Request models
 │    ├── UserResponse.java          # Response models
 │    ├── UserOrderResponse.java
 │    └── User.java
 │
 └── test/java/
      ├── CreateUserTests.java
      ├── LogInUserTests.java
      ├── UpdateUserInfoTests.java
      ├── CreateOrderTests.java
      └── GetUserOrdersTests.java

```

## How to Run

Ensure Maven is installed:
```bash
mvn -version
```

Run the full test suite:
```bash
mvn clean test
```

## Allure Report

Generate interactive Allure report:
```bash
allure serve target/allure-results
```

Generate static HTML report:
```bash
allure generate target/allure-results -o target/allure-report --clean
```

## Related Repositories (Full QA Portfolio)

- UI Automation (Selenium / Selenide)

- API Automation (this repository)

- Cypress E2E tests

- Jest unit tests

- CI/CD pipelines with HTML artifacts

Complete Stellar Burgers QA ecosystem:
https://github.com/reginaniko/stellar-burgers

