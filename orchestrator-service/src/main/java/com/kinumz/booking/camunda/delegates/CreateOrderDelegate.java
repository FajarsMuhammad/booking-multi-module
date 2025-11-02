package com.kinumz.booking.camunda.delegates;

import com.kinumz.booking.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("createOrderDelegate")
@RequiredArgsConstructor
public class CreateOrderDelegate implements JavaDelegate {
    private final OrderService orderService;

    @Override
    public void execute(DelegateExecution execution) {
        String product = (String) execution.getVariable("productCode");
        Integer qty = (Integer) execution.getVariable("quantity");
        Long orderId = orderService.createOrder(product, qty != null ? qty : 1);
        execution.setVariable("orderId", orderId);
    }
}
