package com.kinumz.booking.payment.repository;

import com.kinumz.booking.payment.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {}
