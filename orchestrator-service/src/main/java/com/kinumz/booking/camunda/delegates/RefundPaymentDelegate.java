package com.kinumz.booking.camunda.delegates;

import com.kinumz.booking.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("refundPaymentDelegate")
@RequiredArgsConstructor
public class RefundPaymentDelegate implements JavaDelegate {
    private final PaymentService paymentService;

    @Override
    public void execute(DelegateExecution execution) {
        Long paymentId = (Long) execution.getVariable("paymentId");
        if (paymentId != null) {
            paymentService.refund(paymentId);
        }
    }
}
