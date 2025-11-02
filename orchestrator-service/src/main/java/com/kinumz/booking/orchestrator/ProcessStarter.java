package com.kinumz.booking.orchestrator;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class ProcessStarter implements ApplicationRunner {

    private final RuntimeService runtimeService;

    public ProcessStarter(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @Override
    public void run(ApplicationArguments args) {
        // check if there are any active instances first
        long count = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey("orderSagaProcess")
                .active()
                .count();

        if (count == 0) {
            log.info("⚙️  No active orderSagaProcess found — starting a test instance...");

            runtimeService.startProcessInstanceByKey("orderSagaProcess",
                    Map.of(
                            "productCode", "PROD-001",
                            "quantity", 2,
                            "amount", 120.0
                    ));

            log.info("✅ orderSagaProcess test instance started successfully!");
        } else {
            log.info("ℹ️  Found {} active orderSagaProcess instance(s) — skipping auto-start.", count);
        }
    }
}
