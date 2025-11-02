package com.kinumz.booking.payment.service;

import com.kinumz.booking.payment.entity.PaymentEntity;
import com.kinumz.booking.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;

    @Transactional
    public Long processPayment(Long orderId, Double amount) {
        PaymentEntity p = PaymentEntity.builder()
                .orderId(orderId)
                .amount(amount)
                .status("PAID")
                .build();
        paymentRepository.save(p);
        return p.getId();
    }

    @Transactional
    public void refund(Long paymentId) {
        paymentRepository.findById(paymentId).ifPresent(p -> {
            p.setStatus("REFUNDED");
            paymentRepository.save(p);
        });
    }
}
