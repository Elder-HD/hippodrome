# Hippodrome Project

This project is a simulation of a horse racing event, designed to demonstrate testing, logging, and CI/CD integration in a Java application.

## Task Description

The goal of this project was to enhance the existing Hippodrome simulation by implementing the following:

- **Unit Testing**: Comprehensive tests for all critical functionalities of the `Horse` and `Hippodrome` classes, including parameter validation, method behavior, and edge cases.
- **Logging**: Integrated logging using Log4j2, covering:
  - Error messages for invalid input in constructors.
  - Debug logs for object creation.
  - Informational logs for race progress and results.
- **Continuous Integration**: Configured GitHub Actions to run automated tests and generate test reports.

## Summary of Implemented Features

1. **GitHub Actions**: Added CI configuration to automate testing and reporting.
2. **Testing**: Introduced unit and integration tests with JUnit and Mockito, covering constructor validation, method behavior, and performance.
3. **Logging**: Configured rolling log files with daily rotation and retention of the last 7 days.
4. **Code Coverage**: Added Jacoco plugin to track and report test coverage.
