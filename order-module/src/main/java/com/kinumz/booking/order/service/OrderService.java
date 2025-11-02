package com.kinumz.booking.order.service;

import com.kinumz.booking.order.entity.OrderEntity;
import com.kinumz.booking.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Long createOrder(String productCode, int quantity) {
        OrderEntity o = OrderEntity.builder()
                .productCode(productCode)
                .quantity(quantity)
                .status("CREATED")
                .build();

        orderRepository.save(o);

        return o.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        orderRepository.findById(orderId).ifPresent(o -> {
            o.setStatus("CANCELLED");
            orderRepository.save(o);
        });
    }
}
