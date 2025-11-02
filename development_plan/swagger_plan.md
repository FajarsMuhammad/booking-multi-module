# Development Plan

File: `development_plan/plan.md`

## Project context
- Language: Java
- Build: Maven
- Framework: Spring Boot
- Goal: Deliver a stable REST API with up-to-date documentation using Swagger / OpenAPI

## Objectives
1. Create clear implementation milestones.
2. Integrate OpenAPI/Swagger UI for automatic API documentation.
3. Ensure controllers are annotated and docs reflect real endpoints.
4. Add CI checks and basic integration tests for API docs availability.

## Milestones & Timeline (example)
- Sprint 1 (1 day): Project audit, identify controllers and DTOs.
- Sprint 2 (1–2 days): Add OpenAPI dependency and basic configuration.
- Sprint 3 (1–2 days): Annotate controllers and enrich model/schema.
- Sprint 4 (1 day): Add integration tests and verify endpoints.
- Sprint 5 (half day): CI adjustments, docs and README updates, review.

## Tasks

### 1. Project audit
- List existing controllers, DTOs, global exception handlers, security filters.
- Identify endpoints that need documentation improvements or DTO examples.

### 2. Swagger / OpenAPI integration (core)
- Add OpenAPI dependency to `pom.xml` (example dependency):
    - Use `springdoc-openapi` for Spring Boot:
        - Example (adjust version to project Spring Boot version):
            - `org.springdoc:springdoc-openapi-starter-webmvc-ui` or `org.springdoc:springdoc-openapi-ui` for older setups.
- Create a small configuration class (if custom info is needed) to set title, version, contact.
- Start the app and verify:
    - OpenAPI JSON: `/v3/api-docs`
    - Swagger UI: `/swagger-ui/index.html` (or `/swagger-ui.html` depending on version)

### 3. Annotate and document controllers
- Add `@Operation`, `@Parameter`, `@Schema` where necessary for clarity.
- Add example request/response bodies for complex DTOs using `@ExampleObject` if needed.

### 4. Security considerations
- If API is secured, configure Swagger to accept auth tokens (bearer) in the UI:
    - Add OpenAPI security scheme configuration to enable "Authorize" in UI.
- Restrict UI access in production if required.

### 5. Tests & CI
- Add an integration test that:
    - Starts the app (test profile) and asserts `/v3/api-docs` returns 200 and basic API info.
- Add a CI job step that runs the test and optionally fails the build if docs are missing.

### 6. Documentation & Handoff
- Update `README.md` with:
    - How to run the app locally: `mvn spring-boot:run`
    - Where to access API docs: `/swagger-ui/index.html`
    - How to regenerate or extend docs
- Add short developer notes for annotating new controllers.

## Acceptance criteria
- Local run exposes `/v3/api-docs` with up-to-date schema.
- Swagger UI shows all public endpoints and allows basic try-out.
- Integration test for docs passes in CI.

## Risks & Mitigations
- Version mismatch between Spring Boot and springdoc: pin compatible dependency and test locally.
- Sensitive endpoints exposed in UI: apply environment-based UI disabling or access control.

## Example commands
- Run app: `mvn spring-boot:run`
- Build: `mvn -DskipTests package`

---
