package com.kinumz.booking.camunda.delegates;

import com.kinumz.booking.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("reserveStockDelegate")
@RequiredArgsConstructor
public class ReserveStockDelegate implements JavaDelegate {

    private final InventoryService inventoryService;

    @Override
    public void execute(DelegateExecution execution) {
        String product = (String) execution.getVariable("productCode");
        Integer qty = (Integer) execution.getVariable("quantity");
        boolean ok = inventoryService.reserve(product, qty != null ? qty : 1);
        execution.setVariable("reserved", ok);
        if (!ok) {
            throw new BpmnError("INVENTORY_OUT_OF_STOCK",
                                "Not enough stock available for product: " + product);
        }
    }
}
