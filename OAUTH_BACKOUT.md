# OAuth2 Azure Entra Implementation - Backout Guide

This document tracks all files modified to implement OAuth2 with Azure Entra (Azure AD) for easy backout.

## Files Modified

### 1. `pom.xml`
**Location:** `/Users/yixingma/workspace/sandbox/pom.xml`

**Changes:**
- Added `spring-boot-starter-security` dependency
- Added `spring-boot-starter-oauth2-resource-server` dependency
- Added `spring-boot-starter-actuator` dependency

**To Backout:**
Remove the Spring Security, OAuth2 Resource Server, and Actuator dependencies (lines 33-49), replace with:
```xml
        <!-- security dependencies removed (undo) -->
```

### 2. `SecurityConfig.java`
**Location:** `/Users/yixingma/workspace/sandbox/src/main/java/com/example/security/SecurityConfig.java`

**Changes:**
- Added `@Configuration` and `@EnableWebSecurity` annotations
- Implemented `SecurityFilterChain` bean with OAuth2 resource server configuration
- Configured JWT validation with Azure Entra
- Added security rules for actuator endpoints (health and info are public, others require auth)

**To Backout:**
Replace the entire file content with:
```java
package com.example.security;

/**
 * SecurityConfig was reset by undo operation.
 *
 * If you had a previous SecurityConfig, restore it from your backup or version control.
 */
public class SecurityConfig {

}
```

### 3. `application.properties`
**Location:** `/Users/yixingma/workspace/sandbox/src/main/resources/application.properties`

**Changes:**
- Added Azure Entra OAuth2 configuration properties:
  - `spring.security.oauth2.resourceserver.jwt.issuer-uri`
  - `spring.security.oauth2.resourceserver.jwt.jwk-set-uri`
  - `spring.security.oauth2.resourceserver.jwt.audiences` (for audience validation)
- Added Spring Boot Actuator configuration:
  - `management.endpoints.web.exposure.include`
  - `management.endpoints.web.base-path`
  - `management.endpoint.health.show-details`

**To Backout:**
Remove lines 4-22 (the Azure Entra OAuth2 and Actuator configuration sections)

## Implementation Date
Generated: $(date)

## Notes
- Replace `{tenant-id}` in `application.properties` with your actual Azure Entra tenant ID
- Replace `{client-id}` in `application.properties` with your Azure AD Application (client) ID
- **Client ID is recommended** for audience validation (ensures tokens were issued for your app)
- **Client Secret is NOT needed** - Resource servers validate tokens using public keys, not secrets
- The configuration uses stateless JWT-based authentication
- Actuator endpoints:
  - `/actuator/health` and `/actuator/info` are publicly accessible
  - Other actuator endpoints (metrics, env) require authentication
  - To expose all endpoints, uncomment `management.endpoints.web.exposure.include=*` (use with caution)

