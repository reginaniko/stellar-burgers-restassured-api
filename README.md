# Stellar Burgers — API Automation Test Suite 
### REST Assured • JUnit4 • Allure • Java 17

<p align="left"> <a href="https://github.com/reginaniko/stellar-burgers-restassured-api/actions"> <img src="https://github.com/reginaniko/stellar-burgers-restassured-api/actions/workflows/allure-ci.yml/badge.svg" alt="CI Status" /> </a> <a href="https://reginaniko.github.io/stellar-burgers-restassured-api/"> </a> </p> <p align="left"> <img src="https://img.shields.io/badge/Language-Java%2017-blue?style=for-the-badge" /> <img src="https://img.shields.io/badge/Framework-Rest%20Assured-orange?style=for-the-badge" /> <img src="https://img.shields.io/badge/Test%20Runner-JUnit4-brightgreen?style=for-the-badge" /> <img src="https://img.shields.io/badge/Reporting-Allure%202-purple?style=for-the-badge" /> <img src="https://img.shields.io/badge/Build-Maven-yellow?style=for-the-badge" /> <img src="https://img.shields.io/badge/CI/CD-GitHub%20Actions-2088FF?style=for-the-badge" /> </p>
  
## Interactive Allure Report (GitHub Pages):
https://reginaniko.github.io/stellar-burgers-restassured-api/

(Automatically updated on each push to main)

# Project Overview

This repository contains a production-grade API automation test suite for the Stellar Burgers backend.

It demonstrates:

- Professional API automation design
- Authentication & token-based workflows
- Positive + negative test coverage
- Data model serialization/deserialization
- Reusable HTTP client and clean architecture
- Allure HTML reporting
- GitHub Actions CI pipeline with report publishing

This project is part of the larger Stellar Burgers QA Automation Portfolio, which includes:

- API automation
- Selenium/Selenide UI automation
- Cypress E2E tests
- Jest unit tests
- CI/CD pipelines with reporting
- Project Board (Jira-style) with tasks, labels, CI/CD tickets, bug reports
- Project Wiki with architecture, test strategy, CI/CD pipeline docs

🔗 Full QA portfolio:
https://github.com/reginaniko/stellar-burgers


## Tech Stack

- **Java 17**
- **Rest Assured 5.3**
- **JUnit4**
- **AssertJ / Hamcrest**
- **Lombok**
- **Allure**
- **Maven**
- **Java Faker**
- **GitHub Actions (CI/CD)**

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

## Test Coverage

This suite covers:

- User registration
- Login & authentication
- Update user profile
- Create orders
- Retrieve order history
- Boundary and negative scenarios
- Token management
- Allure report generation for each test

## How to Run Locally

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

## CI/CD Pipeline

This project includes a GitHub Actions pipeline that:

- Runs all REST Assured tests
- Uploads Allure results as workflow artifacts
- Builds the Allure HTML report
- Deploys it automatically to GitHub Pages

Workflow path:
```bash
.github/workflows/allure-ci.yml
```

## Author

Regina Nikogosian — QA Automation Engineer  
USA  
LinkedIn: https://www.linkedin.com/in/regina-nikogosian/





