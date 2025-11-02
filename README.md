# Booking Multi-Module — Complete

This package includes a full multi-module scaffold:
- core-module (Liquibase changelogs)
- order/payment/inventory modules (entities, repos, services)
- orchestrator-service (Camunda engine, REST, delegates)
- admin-module (Camunda webapp/cockpit on port 8081)
- Docker Compose with Postgres, orchestrator, admin, Prometheus, and Grafana
- Prometheus config, Grafana dashboard, Prometheus alert rules
- GitHub Actions CI workflow

Quick start:
1. Start Postgres:
   docker run --name postgres -e POSTGRES_USER=booking -e POSTGRES_PASSWORD=booking -e POSTGRES_DB=camunda -p 5432:5432 -d postgres:15

2. Build & run orchestrator:
   mvn -pl orchestrator-service -am spring-boot:run

3. Start admin module:
   mvn -pl admin-module -am spring-boot:run
   -> Cockpit at http://localhost:8081/camunda

Notes:
- Liquibase will seed camunda group `camunda-admin` and user `demo` (email demo@example.com). You may need to set a password/identity backend depending on your Camunda configuration.
