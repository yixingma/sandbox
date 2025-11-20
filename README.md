# Spring Boot Application

A Spring Boot 3 application that exposes public and protected REST endpoints secured with Azure Entra (Azure AD) OAuth2.

## Prerequisites

- Java 17 or later
- Maven 3.6+

## Build

```bash
mvn clean package
```

## Run

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## Configuration

Set the following environment variables (or override them via `application.yml`) before running locally or deploying:

| Variable | Description | Default |
| --- | --- | --- |
| `AZURE_ISSUER_URI` | Azure Entra issuer URL | `https://login.microsoftonline.com/common/v2.0` |
| `AZURE_JWK_SET_URI` | JWK set URL | `https://login.microsoftonline.com/common/discovery/v2.0/keys` |
| `AZURE_CLIENT_ID` | Application (client) ID used for audience validation | `your-client-id` |

## Endpoints

| Endpoint | Description | Auth |
| --- | --- | --- |
| `GET /api/public/ping` | Health-style endpoint to verify connectivity | None |
| `GET /api/protected/hello` | Protected endpoint that requires a valid Azure Entra JWT | OAuth2 Bearer token |

Example call to the public endpoint:

```bash
curl http://localhost:8080/api/public/ping
# pong
```

Example call to the protected endpoint with a bearer token:

```bash
curl -H "Authorization: Bearer <token>" http://localhost:8080/api/protected/hello
```

## Testing

Run the full test suite (unit + integration + security tests):

```bash
mvn clean test
```
 
