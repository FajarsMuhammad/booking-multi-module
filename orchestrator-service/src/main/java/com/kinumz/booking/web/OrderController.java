package com.kinumz.booking.web;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final RuntimeService runtimeService;

    @PostMapping("/start")
    public ResponseEntity<?> start(@RequestBody Map<String,Object> body) {
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("orderSagaProcess", body);
        return ResponseEntity.ok(Map.of("processInstanceId", pi.getProcessInstanceId()));
    }
}
