package com.kinumz.booking.camunda.delegates;

import com.kinumz.booking.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("processPaymentDelegate")
@RequiredArgsConstructor
public class ProcessPaymentDelegate implements JavaDelegate {
    private final PaymentService paymentService;

    @Override
    public void execute(DelegateExecution execution) {
        Long orderId = (Long) execution.getVariable("orderId");
        Double amount = (Double) execution.getVariable("amount");
        Long paymentId = paymentService.processPayment(orderId, amount != null ? amount : 0.0);
        execution.setVariable("paymentId", paymentId);
    }
}
