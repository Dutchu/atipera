# GitHub Repository Lister

This is a Spring Boot application that provides a REST API to list non-forked repositories for a given GitHub user, along with their
branches and the last commit SHA for each branch.

This project was created as a recruitment task for Atipera.

## Features
*   Lists all public, non-forked repositories for a specified GitHub username.
*   For each repository, it lists all branches with their corresponding last commit SHA.
*   Provides a custom `404 Not Found` error response in JSON format for non-existent users.
*   Supports running over HTTPS using a development profile
*   Handles potential API errors from GitHub, such as rate limiting.
*   Utilizes Java 21 Virtual Threads for improved scalability.

## Technologies Used

*   Java 21
*   Spring Boot 3.5
*   Spring Web
*   Maven
*   JUnit 5 (for integration testing)

### Concurrency Model: Virtual Threads

This application is configured to use **Java 21's Virtual Threads**, enabled via Spring Boot.

**Why is this important?**
This application is I/O-bound, meaning it spends most of its time waiting for network responses from the external GitHub API.

*   **Traditional Model (Platform Threads):** In a traditional setup, the thread handling a request would be blocked while waiting,
consuming a valuable OS thread.
*   **Virtual Threads Model:** When a virtual thread encounters a blocking I/O operation (like an API call), it is "parked," and the
underlying OS thread is freed up to handle other requests.

This allows the application to handle a very high number of concurrent requests with a small number of OS threads, significantly improving throughput and resource efficiency without the complexity of asynchronous programming (like WebFlux). This feature is enabled in
`application.yml`:
```yaml
spring:
  threads:
    virtual:
      enabled: true
```

---

## Prerequisites

Before you begin, ensure you have the following installed:
*   **Java Development Kit (JDK) 21** or later.
*   **Apache Maven** 3.8 or later.

---

## How to Build and Run

You can build and run the project using the included Maven wrapper, which requires no local Maven installation.

### 1. Build the Project

Navigate to the project's root directory in your terminal and run the following command to
compile the code and run the integration tests:
```bash
./mvnw clean install
```
On Windows, use:
```bash
mvnw.cmd clean install
```
This will produce a JAR file in the `target/` directory (e.g., `target/repolister-0.0.1-SNAPSHOT.jar`).

### 2. Run the Application

Once the project is successfully built, you can run the application using the `java -jar` command. 
You have two options for running the application:

#### Option A: Standard (HTTP)

This runs the application on `http://localhost:8080`.

java -jar target/repolister-0.0.1-SNAPSHOT.jar

#### Option B: Development Profile (HTTPS)

---

#### Generating the SSL Certificate (for Dev Profile)

The `dev` profile is configured to run over HTTPS and requires a self-signed SSL certificate. The keystore file (`src/main/resources/keystore/dev-keystore.p12`) is included in this project, so **you should not need to perform this step unless the file is missing or you wish to regenerate it.**

The `keytool` utility used for this is included with the JDK.

### Regeneration Command

This command should be run from the root directory of the project.

```bash
44 keytool -genkeypair -alias dev-cert -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore "src/main/resources/keystore/dev-keystore.p12"
-validity 365 -dname "CN=localhost" -storepass changeit -keypass changeit
```

**How it works:**
*   This command creates a `dev-keystore.p12` file that is configured to work with the settings in `application-dev.yml`.
*   The `-alias`, `-storepass`, and `-keypass` values directly correspond to the `server.ssl.*` properties in the `dev` profile.
*   The `-dname "CN=localhost"` part creates the certificate for the `localhost` domain, allowing you to access `https://localhost` in your
browser or API client

---

This activates the `dev` profile defined in `application-dev.yml`, which enables SSL. The application will run on `https://localhost:8843`.

java -jar -Dspring.profiles.active=dev target/repolister-0.0.1-SNAPSHOT.jar

---

**The application will start, and the server will be running on port `8080` by default.**

## API Usage

The application exposes one primary endpoint.

### Endpoint

`GET /api/find?name={username}`

*   **`{username}`**: The GitHub username for which you want to retrieve repository information.

### Example Request (Success)

You can use `curl` or any API client to make a request.

curl http://localhost:8080/api/find?name=octocat

**Example Success Response (200 OK):**

```json
[
  {
    "repositoryName": "Hello-World",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "master",
        "lastCommitSha": "6dcb09b5b57875f334f61aebed695e2e4193db5e"
      }
    ]
  },
  {
    "repositoryName": "Spoon-Knife",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "d0dd1f61b33d64e29d8bc1372a94ef7a2135c223"
      },
      {
        "name": "test-branch",
        "lastCommitSha": "c3d0be41ecbe669545ee3e94d31ed9a4bc91ee3c"
      }
    ]
  }
]
```

### Example Request (User Not Found)

curl -i http://localhost:8080/api/find?name=this-user-does-not-exist-12345

**Example Error Response (404 Not Found):**

```json
HTTP/1.1 404 Not Found
Content-Type: application/json
{
  "status": "NOT_FOUND",
  "message": "User not found"
}
```