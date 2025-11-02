package com.kinumz.booking.camunda.delegates;

import com.kinumz.booking.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("cancelOrderDelegate")
@RequiredArgsConstructor
public class CancelOrderDelegate implements JavaDelegate {
    private final OrderService orderService;

    @Override
    public void execute(DelegateExecution execution) {
        Long orderId = (Long) execution.getVariable("orderId");
        if (orderId != null) {
            orderService.cancelOrder(orderId);
        }
    }
}
