# Test Application

This repository contains a simple test application that uses Testcontainers for integration testing.

## Prerequisites

- [Docker](https://www.docker.com/) must be installed and running.
- Java 17+ (or the version required by your project).
- An IDE or build tool like IntelliJ, Maven, or Gradle.

## Setup Instructions

1. **Ensure Docker is running**

   Testcontainers relies on Docker to create and manage containers during tests. Please ensure that Docker is installed and running on your system.

2. **Enable Testcontainers Reuse**

   To improve test performance and reuse containers between runs, add the following environment variable:

   On Linux/macOS:
   ```bash
   export TESTCONTAINERS_REUSE_ENABLE=true
   ```
   
    On Windows (Command Prompt):
    ```cmd
    set TESTCONTAINERS_REUSE_ENABLE=true
    ```

    On Windows (PowerShell):
    ```powershell
    $env:TESTCONTAINERS_REUSE_ENABLE="true"
    ```
3. **Run the Application**

    Run the TestApplication class from your IDE or build tool.