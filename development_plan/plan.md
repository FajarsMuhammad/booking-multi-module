# Project Development Plan

## Overview
This project is a multi-module booking system consisting of several microservices:
- admin-module
- core-module
- inventory-module
- orchestrator-service(main module)
- order-module
- payment-module

## Current Features
- Modular architecture using Spring Boot
- Docker Compose for service orchestration
- Database changelogs managed via Liquibase
- Monitoring with Prometheus and Grafana
- BPMN process orchestration (Camunda)

## Planned Features
1. **Authentication & Authorization**
   - Implement JWT-based authentication on orchestrator-service.
   - Secure REST APIs JWT tokens.
   - Add user roles and permissions.
   - Integrate with Spring Security for authentication flows.
2. **API Documentation**
   - Integrate Swagger/OpenAPI for all REST endpoints in each module.
   - Ensure endpoints are documented and accessible via Swagger UI.
3. **Service Communication**
   - Use REST and messaging (e.g., Kafka/RabbitMQ) for inter-service communication.
   - Secure communication channels with JWT and HTTPS.
4. **Testing & Quality Assurance**
   - Add unit, integration, and end-to-end tests for all modules.
   - Use CI/CD pipelines for automated testing and deployment.
5. **Monitoring & Alerting**
   - Expand Prometheus metrics and Grafana dashboards.
   - Add alerting rules for critical events.

## Next Steps
- Design and implement JWT authentication in a shared core module.
- Add Spring Security configuration for JWT validation in each service.
- Update user management flows to support JWT issuance and validation.
- Integrate Swagger UI in all modules and document authentication requirements.
- Update README with authentication and Swagger usage instructions.
- Test authentication and documentation flows end-to-end.

## References
- See `swagger_plan.md` for detailed Swagger integration steps.
- See module-specific documentation for configuration details.

