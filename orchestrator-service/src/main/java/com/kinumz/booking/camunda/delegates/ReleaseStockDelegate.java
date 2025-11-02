package com.kinumz.booking.camunda.delegates;

import com.kinumz.booking.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("releaseStockDelegate")
@RequiredArgsConstructor
public class ReleaseStockDelegate implements JavaDelegate {
    private final InventoryService inventoryService;

    @Override
    public void execute(DelegateExecution execution) {
        String product = (String) execution.getVariable("productCode");
        Integer qty = (Integer) execution.getVariable("quantity");
        inventoryService.release(product, qty != null ? qty : 1);
    }
}
